package com.demo.cityguide.data.datasource.local

import com.demo.cityguide.data.model.PlaceDto

interface LocalDataSource {
    suspend fun getPlaces(): List<PlaceDto>
    suspend fun upsertPlaces(places: List<PlaceDto>)
    suspend fun getLastUpdated(): Long?
    suspend fun saveLastUpdated(timestamp: Long)
}