package com.main.presentation.screens.city_page.components

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.components.weather.WeatherWidget
import com.common.ui.components.weather.utils.toWeatherUI
import com.main.presentation.screens.city_page.state_hoisting.WeatherState

@Composable
fun CurrentWeatherComponent(
    state: WeatherState,
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit
) {

    when(state) {
        is WeatherState.Loading -> {
            LoadingScreen()
        }
        is WeatherState.Error -> {
            Text(text = state.errorMessage)
            Button(onClick = onRetryButtonClick) {
                Text(text = "retry")
            }
        }
        is WeatherState.Content -> {
            WeatherWidget(
                weather = state.weather.toWeatherUI(),
                modifier = modifier
            )
        }
    }
}