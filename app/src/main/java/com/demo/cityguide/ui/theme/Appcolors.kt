package com.demo.cityguide.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,

    val primary: Color,
    val primaryDark: Color,
    val onPrimary: Color,

    val onBackground: Color,
    val onBackgroundMuted: Color,

    val outline: Color,

    val open: Color,
    val closed: Color,

    val isDark: Boolean,
)

val LightColors = AppColors(
    background = Neutral0,
    surface = Neutral5,
    surfaceVariant = Neutral10,
    primary = Primary300,
    primaryDark = Primary500,
    onPrimary = Neutral90,
    onBackground = Neutral90,
    onBackgroundMuted = Neutral40,
    outline = Neutral20,
    open = Success,
    closed = ErrorRed,
    isDark = false,
)

val DarkColors = AppColors(
    background = NeutralDark5,
    surface = NeutralDark10,
    surfaceVariant = NeutralDark20,
    primary = Primary300,
    primaryDark = Primary500,
    onPrimary = Neutral90,
    onBackground = NeutralDark90,
    onBackgroundMuted = NeutralDark40,
    outline = NeutralDark20,
    open = SuccessDark,
    closed = ErrorRedDark,
    isDark = true,
)

val LocalAppColors = staticCompositionLocalOf { LightColors }