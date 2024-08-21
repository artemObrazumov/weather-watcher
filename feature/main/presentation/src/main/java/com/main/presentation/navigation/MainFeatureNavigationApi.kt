package com.main.presentation.navigation

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.main.presentation.screens.main.MainScreen
import com.main.presentation.screens.main.MainScreenViewModel
import com.weatherwatcher.daggerViewModel
import com.weatherwatcher.navigation.NavigationItemApi

class MainFeatureNavigationApi: NavigationItemApi {

    override val route = MainScreen

    override val startDestination = MainScreen

    @SuppressLint("RestrictedApi")
    override fun registerIntoNavigation(
        builder: NavGraphBuilder,
        navController: NavController
    ) {

        builder.composable<MainScreen> {
            val viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(viewModel = viewModel)
        }
    }
}