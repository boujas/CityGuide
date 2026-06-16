package com.demo.cityguide.presentation.home

import com.demo.cityguide.domain.model.PlaceType

sealed interface HomeAction {

    data object LoadPlaces : HomeAction

    data object RetryClick : HomeAction

    data object SyncDialogDismissed : HomeAction

    data class TypeSelected(val type: PlaceType) : HomeAction
}