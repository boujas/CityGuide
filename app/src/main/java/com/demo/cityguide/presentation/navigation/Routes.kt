package com.demo.cityguide.presentation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

@Serializable
data class PlaceDetailsDestination(
    val placeId: String
)