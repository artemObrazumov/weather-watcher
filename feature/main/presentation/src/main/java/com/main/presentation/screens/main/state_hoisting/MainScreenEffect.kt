package com.main.presentation.screens.main.state_hoisting

sealed class MainScreenEffect {
    data object Load: MainScreenEffect()
}