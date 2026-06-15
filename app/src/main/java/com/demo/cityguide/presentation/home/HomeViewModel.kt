package com.demo.cityguide.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cityguide.domain.model.PlaceType
import com.demo.cityguide.domain.model.PlacesSyncResult
import com.demo.cityguide.domain.usecase.GetPlacesUseCase
import com.demo.cityguide.presentation.home.models.SyncDialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPlaces()
    }

    fun loadPlaces() {
        viewModelScope.launch {
            getPlacesUseCase()
                .onSuccess { result ->
                    _uiState.value = HomeUiState.Success(
                        places = result.places,
                        dialogQueue = result.syncResult?.let(::buildDialogQueue).orEmpty()
                    )
                }
                .onFailure { error ->
                    _uiState.value = HomeUiState.Failure(message = error.message ?: "Unknown error")
                }
        }
    }

    fun buildDialogQueue(syncResult: PlacesSyncResult): List<SyncDialogType> =
        buildList {
            if (syncResult.newPlaces.isNotEmpty())
                add(SyncDialogType.New(syncResult.newPlaces))
            if (syncResult.closedPlaces.isNotEmpty())
                add(SyncDialogType.Closed(syncResult.closedPlaces))
        }

    fun dismissCurrentDialog() {
        val current = _uiState.value as? HomeUiState.Success ?: return
        _uiState.value = current.copy(dialogQueue = current.dialogQueue.drop(1))
    }

    fun selectType(type: PlaceType) {
        val current = _uiState.value
        if (current is HomeUiState.Success) {
            _uiState.value = current.copy(selectedType = type)
        }
    }

    fun retry() = loadPlaces()
}