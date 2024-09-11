package com.main.presentation.screens.main.state_hoisting

import com.city.domain.models.City
import kotlinx.coroutines.flow.Flow

data class MainScreenState(
    val citiesState: CitiesState,
    val currentPage: Int
)

sealed class CitiesState {
    data object Loading: CitiesState()
    data class Data(
        val cities: List<City>
    ): CitiesState()
    data class Error(
        val errorMessage: String
    ): CitiesState()
}