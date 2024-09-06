package com.main.presentation.screens.city_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.main.presentation.screens.city_page.state_hoisting.CityPageScreenAction
import com.main.presentation.screens.city_page.state_hoisting.CityPageScreenState

@Composable
fun CityPageScreenContent(
    state: CityPageScreenState,
    modifier: Modifier = Modifier,
    onAction: (CityPageScreenAction) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight()
    ) {
        item(key = "current_weather") {
            CurrentWeatherComponent(
                state = state.currentWeatherState,
                onRetryButtonClick = {
                    onAction(CityPageScreenAction.RetryLoadWeather)
                }
            )
        }
    }
}