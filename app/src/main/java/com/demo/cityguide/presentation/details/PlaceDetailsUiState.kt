package com.demo.cityguide.presentation.details

import com.demo.cityguide.domain.model.Place

sealed interface PlaceDetailsUiState {

    data object Loading : PlaceDetailsUiState

    data class Success(val place: Place) : PlaceDetailsUiState

    data class Failure(val message: String) : PlaceDetailsUiState
}