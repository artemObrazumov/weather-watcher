package com.main.presentation.screens.main.state_hoisting

sealed class MainScreenAction {

    data object AddNewCity: MainScreenAction()
    data class OpenTab(val index: Int): MainScreenAction()
    data class OpenDetails(val id: Int): MainScreenAction()
}