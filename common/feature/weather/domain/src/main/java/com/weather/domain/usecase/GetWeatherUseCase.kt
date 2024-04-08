package com.weather.domain.usecase

import com.weather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ) = weatherRepository.getWeatherForCoordinates(latitude, longitude)
}
