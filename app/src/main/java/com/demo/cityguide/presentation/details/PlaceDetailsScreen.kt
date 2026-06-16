package com.demo.cityguide.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailsScreen(
    uiState: PlaceDetailsUiState,
    onAction: (PlaceDetailsAction) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (uiState) {
                            is PlaceDetailsUiState.Success -> uiState.place.name
                            PlaceDetailsUiState.Loading -> "Loading..."
                            is PlaceDetailsUiState.Failure -> "Details"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                PlaceDetailsUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is PlaceDetailsUiState.Failure -> {
                    Text(uiState.message)
                }

                is PlaceDetailsUiState.Success -> {
                    Text(
                        text = "Деталі для \"${uiState.place.name}\" буде скоро"
                    )
                }
            }
        }
    }
}