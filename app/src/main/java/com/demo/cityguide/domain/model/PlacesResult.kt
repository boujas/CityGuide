package com.demo.cityguide.domain.model

data class PlacesResult(
    val places: List<Place>,
    val syncResult: PlacesSyncResult? = null
)
