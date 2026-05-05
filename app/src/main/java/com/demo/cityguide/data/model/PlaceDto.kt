package com.demo.cityguide.data.model

import androidx.annotation.Keep

@Keep
data class PlaceDto(
    val id: String = "",
    val name: String = "",
    val instagram: String = "",
    val address: String = "",
    val type: String = "",
    val isActive: Boolean = true
)