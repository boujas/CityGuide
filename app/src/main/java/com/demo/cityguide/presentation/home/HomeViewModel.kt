package com.demo.cityguide.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cityguide.domain.model.PlaceType
import com.demo.cityguide.domain.usecase.GetPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPlacesUseCase: GetPlacesUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPlaces()
    }

    fun loadPlaces() {
        viewModelScope.launch {
            getPlacesUseCase()
                .onSuccess { places ->
                    _uiState.value = HomeUiState.Success(places = places)
                }
                .onFailure { error ->
                    _uiState.value = HomeUiState.Failure(message = error.message ?: "Unknown error")
                }
        }
    }

    fun selectType(type: PlaceType) {
        val current = _uiState.value
        if (current is HomeUiState.Success) {
            _uiState.value = current.copy(selectedType = type)
        }
    }

    fun retry() = loadPlaces()
}