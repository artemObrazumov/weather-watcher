package com.weather.domain.usecase

import com.weather.domain.models.weather.Weather
import com.weather.domain.models.weather.WeatherLog
import com.weather.domain.models.weather.WeatherLogEntry
import com.weather.domain.repository.WeatherRepository
import java.time.LocalDateTime
import javax.inject.Inject

class LogWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        weatherLogEntry: WeatherLogEntry
    ) = when(weatherLogEntry) {
        is WeatherLogEntry.UnsuccessfulLog -> {
            saveUnsuccessfulWeatherLog(
                cityId = weatherLogEntry.cityId,
                errorMessage = weatherLogEntry.errorMessage,
                time = weatherLogEntry.time
            )
        }
        is WeatherLogEntry.SuccessfulLog -> {
            saveWeatherLog(weatherLogEntry.weatherLog)
        }
    }

    private suspend fun saveWeatherLog(
        weather: WeatherLog
    ) {
        weatherRepository.logWeather(weather)
    }

    private suspend fun saveUnsuccessfulWeatherLog(
        cityId: Int,
        errorMessage: String,
        time: LocalDateTime,
    ) {
        weatherRepository.logUnsuccessfulWeatherLog(cityId, errorMessage, time)
    }
}