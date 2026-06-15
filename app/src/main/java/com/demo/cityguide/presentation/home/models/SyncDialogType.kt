package com.demo.cityguide.presentation.home.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.demo.cityguide.R
import com.demo.cityguide.domain.model.Place

sealed class SyncDialogType(
    @field:StringRes val titleRes: Int,
    @field:StringRes val buttonTextRes: Int,
    @field:DrawableRes val iconRes: Int,
    val color: @Composable () -> Color,
    val places: List<Place>
) {
    data class New(private val newPlaces: List<Place>) : SyncDialogType(
        titleRes = R.string.sync_dialog_new_title,
        buttonTextRes = R.string.sync_dialog_new_button,
        iconRes = R.drawable.ic_random,
        color = { MaterialTheme.colorScheme.primary },
        places = newPlaces
    )

    data class Closed(private val closedPlaces: List<Place>) : SyncDialogType(
        titleRes = R.string.sync_dialog_closed_title,
        buttonTextRes = R.string.sync_dialog_closed_button,
        iconRes = R.drawable.ic_random,
        color = { MaterialTheme.colorScheme.error },
        places = closedPlaces
    )
}