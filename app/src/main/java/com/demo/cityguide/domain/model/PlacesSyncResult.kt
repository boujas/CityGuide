package com.demo.cityguide.domain.model

data class PlacesSyncResult(
    val newPlaces: List<Place> = emptyList(),
    val closedPlaces: List<Place> = emptyList()
) {
    val hasChanges: Boolean
        get() = newPlaces.isNotEmpty() || closedPlaces.isNotEmpty()
}