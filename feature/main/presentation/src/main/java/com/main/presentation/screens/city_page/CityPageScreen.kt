package com.main.presentation.screens.city_page

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_page.components.CityPageScreenContent

@Composable
fun CityPageScreen(
    viewModel: CityPageScreenViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    CityPageScreenContent(
        state = state,
        modifier = modifier,
        onAction = viewModel::onAction
    )
}