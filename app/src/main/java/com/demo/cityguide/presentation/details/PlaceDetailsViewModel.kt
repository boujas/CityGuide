package com.demo.cityguide.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.demo.cityguide.domain.usecase.GetPlaceDetailsUseCase
import com.demo.cityguide.presentation.navigation.PlaceDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase
) : ViewModel() {

    private val route = savedStateHandle.toRoute<PlaceDetailsDestination>()

    private val _uiState = MutableStateFlow<PlaceDetailsUiState>(PlaceDetailsUiState.Loading)
    val uiState: StateFlow<PlaceDetailsUiState> = _uiState.asStateFlow()

    init {
        onAction(PlaceDetailsAction.LoadPlace)
    }

    fun onAction(action: PlaceDetailsAction) {
        when (action) {
            PlaceDetailsAction.LoadPlace -> loadPlace()
        }
    }

    private fun loadPlace() {
        _uiState.value = PlaceDetailsUiState.Loading

        viewModelScope.launch {
            getPlaceDetailsUseCase(route.placeId)
                .onSuccess { place ->
                    _uiState.value = PlaceDetailsUiState.Success(place)
                }
                .onFailure { error ->
                    _uiState.value = PlaceDetailsUiState.Failure(
                        message = error.message ?: "Unknown error"
                    )
                }
        }
    }
}