package com.main.presentation.screens.city_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityInfoState

@Composable
fun CityInfoPage(
    state: CityInfoState,
    modifier: Modifier = Modifier,
    onAction: (CityInfoScreenAction) -> Unit
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        when (state) {
            is CityInfoState.Data -> {
                RoundedContainer(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Название",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = state.city.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
                Row {
                    RoundedContainer(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Ширина",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.city.latitude.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                    RoundedContainer(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Долгота",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.city.longitude.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.large))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAction(CityInfoScreenAction.EditCity) }
                ) {
                    Text(text = "Редактировать город")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = MaterialTheme.colorScheme.error),
                    onClick = { onAction(CityInfoScreenAction.DeleteCity) }
                ) {
                    Text(text = "Удалить город")
                }
            }
            is CityInfoState.Loading -> {
                LoadingScreen()
            }
            is CityInfoState.Error -> {}
        }
    }
}