package com.main.presentation.screens.city_editor

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_editor.components.CityEditorScreenContent
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenEffect
import com.main.presentation.screens.main.state_hoisting.MainScreenEffect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun CityEditorScreen(
    navController: NavController,
    viewModel: CityEditorScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is CityEditorScreenEffect.OpenMap -> {

                }
                is CityEditorScreenEffect.NavigateBack -> {
                    navController.navigateUp()
                }
            }
        }.flowOn(EmptyCoroutineContext).launchIn(this)
    }

    CityEditorScreenContent(
        state = state,
        modifier = Modifier.padding(horizontal = WeatherWatcherTheme.paddings.medium),
        onAction = viewModel::onAction
    )
}