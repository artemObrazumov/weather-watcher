package com.main.presentation.navigation

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.main.presentation.screens.city_editor.CityEditorScreen
import com.main.presentation.screens.city_editor.CityEditorScreenViewModel
import com.main.presentation.screens.main.MainScreen
import com.main.presentation.screens.main.MainScreenViewModel
import com.weatherwatcher.navigation.NavigationItemApi
import kotlinx.serialization.Serializable
import javax.inject.Inject

class MainFeatureNavigationApi @Inject constructor(): NavigationItemApi {

    override val route = MainScreen

    override val startDestination = MainScreen

    @SuppressLint("RestrictedApi")
    override fun registerIntoNavigation(
        builder: NavGraphBuilder,
        navController: NavController
    ) {
        builder.composable<MainScreen> {
            val viewModel: MainScreenViewModel = hiltViewModel()
            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        builder.composable<CityEditorScreen> { backStackEntry ->
            val cityEditorScreen: CityEditorScreen = backStackEntry.toRoute()
            val viewModel: CityEditorScreenViewModel =
                hiltViewModel<CityEditorScreenViewModel, CityEditorScreenViewModel.Factory>(
                    creationCallback = { factory -> factory.create(cityEditorScreen.cityId) }
                )
            CityEditorScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Serializable
object MainScreen

@Serializable
data class CityEditorScreen(
    val cityId: Int
)