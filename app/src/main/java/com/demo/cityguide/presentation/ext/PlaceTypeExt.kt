package com.demo.cityguide.presentation.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.demo.cityguide.R
import com.demo.cityguide.domain.model.PlaceType

@get:Composable
val PlaceType.icon: Painter
    get() = when (this) {
        PlaceType.COFFEE -> painterResource(R.drawable.ic_coffee)
        PlaceType.BAR -> painterResource(R.drawable.ic_bar)
        PlaceType.CAFE -> painterResource(R.drawable.ic_restaurant)
    }

val PlaceType.label: String
    get() = when (this) {
        PlaceType.COFFEE -> "Coffee shops"
        PlaceType.BAR -> "Bars"
        PlaceType.CAFE -> "Cafes"
    }