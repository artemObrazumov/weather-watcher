package com.weather.domain.usecase

import com.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ) = weatherRepository.getWeatherForCoordinates(latitude, longitude)
}
