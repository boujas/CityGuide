package com.demo.cityguide.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val LightM3Scheme = lightColorScheme(
    primary = Primary300,
    onPrimary = Neutral90,
    background = Neutral0,
    onBackground = Neutral90,
    surface = Neutral5,
    onSurface = Neutral90,
    surfaceVariant = Neutral10,
    onSurfaceVariant = Neutral40,
    outline = Neutral20,
    error = ErrorRed,
    onError = Neutral0,
)

private val DarkM3Scheme = darkColorScheme(
    primary = Primary300,
    onPrimary = Neutral90,
    background = NeutralDark5,
    onBackground = NeutralDark90,
    surface = NeutralDark10,
    onSurface = NeutralDark90,
    surfaceVariant = NeutralDark20,
    onSurfaceVariant = NeutralDark40,
    outline = NeutralDark20,
    error = ErrorRedDark,
    onError = Neutral0,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColors = if (darkTheme) DarkColors else LightColors
    val m3Scheme = if (darkTheme) DarkM3Scheme else LightM3Scheme

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = m3Scheme,
            typography = AppTypography,
            content = content,
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable @ReadOnlyComposable
        get() = LocalAppColors.current
}