package com.demo.cityguide.domain.repository

import com.demo.cityguide.domain.model.Place

interface PlacesRepository {

    suspend fun getPlaces(): List<Place>

}