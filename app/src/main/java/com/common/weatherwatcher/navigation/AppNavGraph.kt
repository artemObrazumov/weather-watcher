package com.common.weatherwatcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.weatherwatcher.navigation.NavigationItemApi

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationApis: Set<NavigationItemApi>
) {
    NavHost(
        navController = navController,
        startDestination = navigationApis.first().startDestination,
        modifier = Modifier
    ) {
        navigationApis.forEach { api ->
            api.registerIntoNavigation(
                builder = this,
                navController = navController
            )
        }
    }
}