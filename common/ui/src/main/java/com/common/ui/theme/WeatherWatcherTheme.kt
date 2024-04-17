package com.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

data class WeatherWatcherPaddings (
    val small: Dp,
    val default: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

object WeatherWatcherTheme {

    val paddings: WeatherWatcherPaddings
        @Composable
        get() = LocalPaddings.current
}

val LocalPaddings =
    staticCompositionLocalOf<WeatherWatcherPaddings> { error("No paddings provided") }