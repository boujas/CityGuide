package com.demo.cityguide.presentation.ext

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.demo.cityguide.domain.model.PlaceType

val PlaceType.icon: ImageVector
    get() = when (this) {
        PlaceType.COFFEE -> Icons.Default.Star
        PlaceType.BAR -> Icons.Default.Home
        PlaceType.CAFE -> Icons.Default.Place
    }

val PlaceType.label: String
    get() = when (this) {
        PlaceType.COFFEE -> "Coffee"
        PlaceType.BAR -> "Bars"
        PlaceType.CAFE -> "Cafes"
    }