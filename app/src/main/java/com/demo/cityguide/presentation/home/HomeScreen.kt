package com.demo.cityguide.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.cityguide.R
import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.model.PlaceType
import com.demo.cityguide.presentation.ext.icon
import com.demo.cityguide.presentation.ext.label
import com.demo.cityguide.presentation.utils.openGoogleMaps
import com.demo.cityguide.presentation.utils.openInstagram

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showRandomDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("What's your pick today?")
                }
            )
        },
        bottomBar = {
            if (uiState is HomeUiState.Success) {
                BottomBar(
                    selectedType = (uiState as HomeUiState.Success).selectedType,
                    onTypeSelected = viewModel::selectType
                )
            }
        },
        floatingActionButton = {
            if (uiState is HomeUiState.Success) {
                LargeFloatingActionButton(
                    onClick = { showRandomDialog = true },
                    containerColor = MaterialTheme.colorScheme.error,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Random Place",
                        tint = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is HomeUiState.Failure -> ErrorContent(message = state.message) // TODO Add retry
            HomeUiState.Idle -> Unit
            HomeUiState.Loading -> LoadingContent()
            is HomeUiState.Success -> {
                SuccessContent(
                    places = state.filteredPlaces,
                    modifier = Modifier.padding(innerPadding)
                )

                if (showRandomDialog) {
                    RandomPlaceDialog(
                        places = state.filteredPlaces,
                        onDismiss = { showRandomDialog = false }
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun SuccessContent(places: List<Place>, modifier: Modifier) {
    if (places.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Oops, no places")
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(places, key = { it.id }) { place ->
            PlaceItem(place = place)
        }
    }
}

@Composable
fun BottomBar(selectedType: PlaceType, onTypeSelected: (PlaceType) -> Unit) {
    NavigationBar(modifier = Modifier.height(80.dp))
    {
        PlaceType.entries.forEach { type ->
            NavigationBarItem(
                selected = selectedType == type,
                onClick = { onTypeSelected(type) },
                icon = {
                    Icon(
                        imageVector = type.icon,
                        contentDescription = type.label
                    )
                }
            )
        }
    }
}

@Composable
fun PlaceItem(place: Place) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = place.address,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "@${place.instagram}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { openInstagram(context, place.instagram) }
                )
            }

            IconButton(onClick = { openGoogleMaps(context, place.address) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Open Google Maps",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContentPreview() {
    HomeScreen()
}