package com.demo.cityguide.domain.model

data class Place(
    val id: String,
    val name: String,
    val instagram: String,
    val address: String,
    val type: PlaceType,
    val isActive: Boolean
)
