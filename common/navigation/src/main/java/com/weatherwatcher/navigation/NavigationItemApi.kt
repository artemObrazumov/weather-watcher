package com.weatherwatcher.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavigationItemApi {

    val route: Any
    val startDestination: Any

    fun registerIntoNavigation(
        builder: NavGraphBuilder,
        navController: NavController
    )
}