package com.main.presentation.screens.main.state_hoisting

sealed class MainScreenEffect {
    data object NavigateToAddNewCity: MainScreenEffect()
    data class NavigateToCityDetails(
        val cityId: Int
    ): MainScreenEffect()
}