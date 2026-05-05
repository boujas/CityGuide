package com.demo.cityguide.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cityguide.domain.usecase.GetPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPlacesUseCase: GetPlacesUseCase) :
    ViewModel() {

    init {
        getPlaces()
    }

    fun getPlaces() {
        viewModelScope.launch {
            getPlacesUseCase()
                .onSuccess {
                    for (place in it) {
                        println(place.name)
                    }
                }
                .onFailure { error ->
                    println("Error: " + error.message)
                }
        }
    }

}