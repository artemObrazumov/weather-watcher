package com.main.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.main.presentation.screens.main.components.MainScreenContent
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

@Serializable
object MainScreen

@Serializable
object Root