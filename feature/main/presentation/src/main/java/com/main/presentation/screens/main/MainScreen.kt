package com.main.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.main.presentation.screens.main.components.MainScreenContentState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.presentation.screens.main.state_hoisting.MainScreenState
import kotlinx.serialization.Serializable

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    MainScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MainScreenContent(
    state: MainScreenState,
    onAction: (MainScreenAction) -> Unit
) {

    when(state) {
        is MainScreenState.Content -> {
            MainScreenContentState(
                citiesSectionState = state.citiesSectionState,
                onAction = onAction
            )
        }
    }
}

@Serializable
object MainScreen

@Serializable
object Root