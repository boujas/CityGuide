package com.demo.cityguide.data.repository

import com.demo.cityguide.data.mapper.PlaceMapper
import com.demo.cityguide.data.datasource.remote.RemoteDataSource
import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val placeMapper: PlaceMapper
) :
    PlacesRepository {
    override suspend fun getPlaces(): List<Place> {
        return remote.getAllPlaces()
            .map(placeMapper::toDomain)
            .filter { it.isActive }
    }
}