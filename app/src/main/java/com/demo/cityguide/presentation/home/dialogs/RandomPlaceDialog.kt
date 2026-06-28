package com.demo.cityguide.presentation.home.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.demo.cityguide.R
import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun RandomPlaceDialog(places: List<Place>, onDismiss: () -> Unit) {
    var currentName by remember { mutableStateOf("") }
    var isSpinning by remember { mutableStateOf(true) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }

    LaunchedEffect(Unit) {
        val spinDuration = 2500L
        val startTime = System.currentTimeMillis()

        while (System.currentTimeMillis() - startTime < spinDuration) {
            val elapsed = System.currentTimeMillis() - startTime
            val progress = elapsed.toFloat() / spinDuration

            val delay = (50 + (progress * 250)).toLong()

            currentName = places.random().name

            delay(delay.milliseconds)
        }

        selectedPlace = places.random()
        currentName = selectedPlace!!.name
        isSpinning = false
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.random_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    if (isSpinning) {
                        SpinningName(name = currentName)
                    } else {
                        RevealedName(name = currentName)
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = !isSpinning,
                    enter = fadeIn() + expandVertically()
                ) {
                    selectedPlace?.let { place ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = place.address,
                                style = MaterialTheme.typography.bodyMedium,
                                color = AppTheme.colors.onBackgroundMuted,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = place.instagram,
                                style = MaterialTheme.typography.bodySmall,
                                color = AppTheme.colors.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                AnimatedVisibility(
                    visible = !isSpinning,
                    enter = fadeIn() + slideInVertically()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            border = BorderStroke(1.dp, AppTheme.colors.primary),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = AppTheme.colors.primary,
                            )
                        ) {
                            Text(stringResource(R.string.random_dialog_btn_close))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SpinningName(name: String) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(80),
        label = "alpha"
    )
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = AppTheme.colors.onBackground.copy(alpha = alpha),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RevealedName(name: String) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "scale"
    )
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = AppTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { scaleX = scale; scaleY = scale }
    )
}