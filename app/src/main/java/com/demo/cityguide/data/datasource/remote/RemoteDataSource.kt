package com.demo.cityguide.data.datasource.remote

import com.demo.cityguide.data.model.PlaceDto
import java.util.Date

interface RemoteDataSource {

    suspend fun getAllPlaces(): List<PlaceDto>

    suspend fun getLastUpdated(): Date?

    suspend fun addPlace(place: PlaceDto)

}