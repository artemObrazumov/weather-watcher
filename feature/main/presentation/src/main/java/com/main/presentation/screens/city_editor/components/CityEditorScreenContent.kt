package com.main.presentation.screens.city_editor.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.common.ui.components.wrapper.ClickableRoundedRow
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenAction
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityEditorScreenContent(
    state: CityEditorScreenState,
    modifier: Modifier = Modifier,
    onAction: (CityEditorScreenAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (state.isAddingNewCity) "Добавление города"
                        else "Редактирование города"
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column {
                OutlinedTextField(
                    value = state.cityNameState.text,
                    label = {
                        Text(text = "Название города")
                    },
                    isError = state.cityNameState.errorMessage != null,
                    supportingText = {
                        if (state.cityNameState.errorMessage != null) {
                            Text(
                                text = state.cityNameState.errorMessage ?: ""
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    onValueChange = { onAction(CityEditorScreenAction.OnCityNameUpdated(it)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.large))
                Text(
                    text = "Координаты города",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
                ClickableRoundedRow(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(CityEditorScreenAction.OnMapOpened)
                    }
                ) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Выбрать место на карте",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    OutlinedTextField(
                        value = state.cityXState.text,
                        label = {
                            Text(text = "X")
                        },
                        isError = state.cityXState.errorMessage != null,
                        supportingText = {
                            if (state.cityXState.errorMessage != null) {
                                Text(
                                    text = state.cityXState.errorMessage ?: ""
                                )
                            }
                        },
                        modifier = Modifier
                            .weight(1f),
                        onValueChange = { onAction(CityEditorScreenAction.OnCityXUpdated(it)) },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Decimal
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    OutlinedTextField(
                        value = state.cityYState.text,
                        label = {
                            Text(text = "Y")
                        },
                        isError = state.cityYState.errorMessage != null,
                        supportingText = {
                            if (state.cityYState.errorMessage != null) {
                                Text(
                                    text = state.cityYState.errorMessage ?: ""
                                )
                            }
                        },
                        modifier = Modifier
                            .weight(1f),
                        onValueChange = { onAction(CityEditorScreenAction.OnCityYUpdated(it)) },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal
                        )
                    )
                }
                Button(
                    onClick = { onAction(CityEditorScreenAction.OnCitySaved) }
                ) {
                    Text(text = "Сохранить город")
                }
            }
        }
    }
}