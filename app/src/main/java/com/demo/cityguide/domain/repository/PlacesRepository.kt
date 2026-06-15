package com.demo.cityguide.domain.repository

import com.demo.cityguide.domain.model.PlacesResult

interface PlacesRepository {

    suspend fun getPlaces(): PlacesResult

}