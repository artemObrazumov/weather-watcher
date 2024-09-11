package com.main.presentation.screens.city_info.state_hoisting

import com.city.domain.models.City

data class CityInfoScreenState(
    val cityInfoState: CityInfoState,
    val cityMonitoringState: CityMonitoringState,
    val isProcessingOperation: Boolean,
    val activeTab: Int
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

sealed class CityMonitoringState {
    data object Loading: CityMonitoringState()
    data class Error(
        val errorMessage: String
    ): CityMonitoringState()
    data class Data(
        val data: Any
    ): CityMonitoringState()
}