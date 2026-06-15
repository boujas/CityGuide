package com.demo.cityguide.data.model

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class PlaceDto(
    val id: String = "",
    val name: String = "",
    val instagram: String = "",
    val address: String = "",
    val type: String = "",

    @get:PropertyName("isActive")
    val isActive: Boolean = true
)