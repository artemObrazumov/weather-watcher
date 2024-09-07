package com.main.presentation.screens.city_info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.main.presentation.screens.city_editor.CityEditorScreen
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenEffect
import com.main.presentation.screens.city_info.components.CityInfoScreenContent
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenEffect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun CityInfoScreen(
    navController: NavController,
    viewModel: CityInfoScreenViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is CityInfoScreenEffect.NavigateBack -> {
                    navController.navigateUp()
                }

                is CityInfoScreenEffect.NavigateToCityEditScreen -> {
                    navController.navigate(CityEditorScreen(effect.id))
                }
            }
        }.flowOn(EmptyCoroutineContext).launchIn(this)
    }

    CityInfoScreenContent(
        state = state,
        modifier = modifier,
        onAction = viewModel::onAction
    )
}

@Serializable
data class CityInfoScreen(
    val cityId: Int
)