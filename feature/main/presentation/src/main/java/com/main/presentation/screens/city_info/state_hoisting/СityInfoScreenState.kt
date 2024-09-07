package com.main.presentation.screens.city_info.state_hoisting

import com.city.domain.models.City

data class CityInfoScreenState(
    val cityInfoState: CityInfoState,
    val isProcessingOperation: Boolean
)

sealed class CityInfoState {
    data object Loading: CityInfoState()
    data class Error(
        val errorMessage: String
    ): CityInfoState()
    data class Data(
        val city: City
    ): CityInfoState()
}