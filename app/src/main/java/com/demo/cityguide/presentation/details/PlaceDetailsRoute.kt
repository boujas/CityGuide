package com.demo.cityguide.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PlaceDetailsRoute(
    viewModel: PlaceDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PlaceDetailsScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}