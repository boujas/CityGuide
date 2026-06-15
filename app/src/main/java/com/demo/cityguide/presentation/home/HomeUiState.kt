package com.demo.cityguide.presentation.home

import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.model.PlaceType
import com.demo.cityguide.presentation.home.models.SyncDialogType

sealed interface HomeUiState {

    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Success(
        val places: List<Place>,
        val selectedType: PlaceType = PlaceType.BAR,
        val dialogQueue: List<SyncDialogType> = emptyList()
    ) : HomeUiState {
        val filteredPlaces: List<Place>
            get() = places.filter { it.type == selectedType }

        val currentDialog: SyncDialogType? get() = dialogQueue.firstOrNull()
    }

    data class Failure(val message: String) : HomeUiState
}