package com.main.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.main.presentation.navigation.CityEditorScreen
import com.main.presentation.screens.main.components.MainScreenContent
import com.main.presentation.screens.main.state_hoisting.MainScreenEffect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is MainScreenEffect.NavigateToAddNewCity -> {
                    navController.navigate(CityEditorScreen(-1))
                }
            }
        }.flowOn(EmptyCoroutineContext).launchIn(this)
    }

    MainScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}