package com.main.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.main.presentation.screens.main.MainScreen
import com.main.presentation.screens.main.MainScreenViewModel
import com.main.presentation.screens.main.Root
import com.weatherwatcher.navigation.NavigationItemApi
import javax.inject.Inject

class MainFeatureNavigationApi: NavigationItemApi {

    override val route = MainScreen

    override val startDestination = MainScreen

    @SuppressLint("RestrictedApi")
    override fun registerIntoNavigation(
        builder: NavGraphBuilder,
        navController: NavController
    ) {
        builder.composable<MainScreen> {
            val viewModel: MainScreenViewModel
            MainScreen(viewModel = viewModel)
        }
    }
}