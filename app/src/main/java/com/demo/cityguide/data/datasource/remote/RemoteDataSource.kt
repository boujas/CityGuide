package com.demo.cityguide.data.datasource.remote

import com.demo.cityguide.data.model.PlaceDto

interface RemoteDataSource {

    suspend fun getAllPlaces(): List<PlaceDto>

    suspend fun addPlace(place: PlaceDto)

}