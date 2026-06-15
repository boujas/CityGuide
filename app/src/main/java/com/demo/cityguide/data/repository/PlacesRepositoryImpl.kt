package com.demo.cityguide.data.repository

import com.demo.cityguide.data.datasource.local.LocalDataSource
import com.demo.cityguide.data.mapper.PlaceMapper
import com.demo.cityguide.data.datasource.remote.RemoteDataSource
import com.demo.cityguide.domain.model.PlacesResult
import com.demo.cityguide.domain.model.PlacesSyncResult
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val mapper: PlaceMapper
) :
    PlacesRepository {
    override suspend fun getPlaces(): PlacesResult {
        return try {
            val remoteTimestamp = remote.getLastUpdated()
            val localTimestamp = local.getLastUpdated()
            val localPlaces = local.getPlaces()

            val needsUpdate = remoteTimestamp == null ||
                    localTimestamp == null ||
                    remoteTimestamp.time > localTimestamp

            if (!needsUpdate) {
                return PlacesResult(
                    places = localPlaces.map(mapper::toDomain).filter { it.isActive }
                )
            }

            val remotePlaces = remote.getAllPlaces()

            val syncedResult = if (localPlaces.isNotEmpty()) {
                val localIds = localPlaces.map { it.id }.toSet()

                PlacesSyncResult(
                    newPlaces = remotePlaces
                        .filter { it.id !in localIds }
                        .map(mapper::toDomain),
                    closedPlaces = remotePlaces
                        .filter { !it.isActive }
                        .filter { localPlaces.any { local -> local.id == it.id && local.isActive } }
                        .map(mapper::toDomain)
                ).takeIf { it.hasChanges }
            } else null

            local.upsertPlaces(remotePlaces)
            remoteTimestamp?.let { local.saveLastUpdated(it.time) }

            PlacesResult(
                places = remotePlaces.map(mapper::toDomain).filter { it.isActive },
                syncResult = syncedResult
            )
        } catch (e: Exception) {
            val cache = local.getPlaces()
            if (cache.isNotEmpty()) PlacesResult(places = cache.map(mapper::toDomain))
            else throw e
        }
    }

}