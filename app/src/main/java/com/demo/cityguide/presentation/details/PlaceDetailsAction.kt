package com.demo.cityguide.presentation.details

sealed interface PlaceDetailsAction {

    data object LoadPlace : PlaceDetailsAction

}