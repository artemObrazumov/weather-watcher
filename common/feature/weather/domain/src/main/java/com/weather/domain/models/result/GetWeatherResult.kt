package com.weather.domain.models.result

import com.weather.domain.models.weather.Weather

sealed class GetWeatherResult {

    data class Success(val weather: Weather): GetWeatherResult()
    data class Failure(val errorMessage: String): GetWeatherResult()
}
