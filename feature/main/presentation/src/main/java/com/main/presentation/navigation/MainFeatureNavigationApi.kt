package com.main.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.main.presentation.screens.city_editor.CityEditorScreen
import com.main.presentation.screens.city_editor.CityEditorScreenViewModel
import com.main.presentation.screens.city_info.CityInfoScreen
import com.main.presentation.screens.city_info.CityInfoScreenViewModel
import com.main.presentation.screens.main.MainScreen
import com.main.presentation.screens.main.MainScreenViewModel
import com.weatherwatcher.navigation.NavigationItemApi
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

        builder.composable<CityEditorScreen>(
            enterTransition = { slideInHorizontally(initialOffsetX = { it/2 }) + fadeIn() },
            exitTransition = { slideOutHorizontally() + fadeOut() }
        ) { backStackEntry ->
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

        builder.composable<CityInfoScreen>(
            enterTransition = { slideInVertically() + fadeIn() },
            exitTransition = { slideOutVertically() + fadeOut() }
        ) { backStackEntry ->

            val cityInfoScreen: CityInfoScreen = backStackEntry.toRoute()
            val viewModel: CityInfoScreenViewModel =
                hiltViewModel<CityInfoScreenViewModel, CityInfoScreenViewModel.Factory>(
                    creationCallback = { factory -> factory.create(cityInfoScreen.cityId) }
                )

            CityInfoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}