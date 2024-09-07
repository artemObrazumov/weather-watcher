package com.main.presentation.screens.city_info.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.theme.Red40
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenState
import com.main.presentation.screens.city_info.state_hoisting.CityInfoState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityInfoScreenContent(
    state: CityInfoScreenState,
    modifier: Modifier = Modifier,
    onAction: (CityInfoScreenAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when(state.cityInfoState) {
                            is CityInfoState.Data -> state.cityInfoState.city.name
                            else -> ""
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier
            .padding(innerPadding)
            .padding(horizontal = WeatherWatcherTheme.paddings.medium)
            .fillMaxSize()
        ) {
            when(state.cityInfoState) {
                is CityInfoState.Loading -> {
                    LoadingScreen()
                }
                is CityInfoState.Data -> {
                    Column {
                        Text(
                            text = "Название",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.cityInfoState.city.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
                        Row {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Ширина",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = state.cityInfoState.city.latitude.toString(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.extraLarge))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Долгота",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = state.cityInfoState.city.longitude.toString(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.extraLarge))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onAction(CityInfoScreenAction.EditCity) }
                        ) {
                            Text(text = "Редактировать город")
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors().copy(containerColor = Red40),
                            onClick = { onAction(CityInfoScreenAction.DeleteCity) }
                        ) {
                            Text(text = "Удалить город")
                        }
                    }
                }
                is CityInfoState.Error -> {}
            }
        }
    }
}