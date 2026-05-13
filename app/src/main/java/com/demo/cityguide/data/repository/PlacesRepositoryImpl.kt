package com.demo.cityguide.data.repository

import com.demo.cityguide.data.datasource.local.LocalDataSource
import com.demo.cityguide.data.mapper.PlaceMapper
import com.demo.cityguide.data.datasource.remote.RemoteDataSource
import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val mapper: PlaceMapper
) :
    PlacesRepository {
    override suspend fun getPlaces(): List<Place> {
        return try {
            val remoteTimestamp = remote.getLastUpdated()
            val localTimestamp = local.getLastUpdated()

            val needsUpdate = remoteTimestamp == null ||
                    localTimestamp == null ||
                    remoteTimestamp.time > localTimestamp

            if (needsUpdate) {
                val result = remote.getAllPlaces()
                local.upsertPlaces(result)
                remoteTimestamp?.let { local.saveLastUpdated(it.time) }
                result.map(mapper::toDomain)
                    .filter { it.isActive }
            } else {
                local.getPlaces().map(mapper::toDomain)
            }
        } catch (e: Exception) {
            val cache = local.getPlaces()
            if (cache.isNotEmpty()) {
                cache.map(mapper::toDomain)
            } else {
                throw e
            }
        }
    }

}