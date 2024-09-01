package com.main.presentation.screens.main.state_hoisting

import com.city.domain.models.City
import kotlinx.coroutines.flow.Flow

sealed class MainScreenState {
    data class Data(
        val cities: Flow<List<City>>,
        val currentPage: Int
    ) : MainScreenState()

    data object Loading : MainScreenState()
}