package com.weather.domain.test.usecase

import com.weather.domain.repository.WeatherRepository
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather
import com.weather.domain.usecase.GetWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetWeatherUseCaseTest {

    @Test
    fun `should return failure result`() = runTest {
        val mockWeatherRepository = mockk<WeatherRepository>()
        val getWeatherUseCase = GetWeatherUseCase(mockWeatherRepository)

        coEvery {
            mockWeatherRepository.getWeatherForCoordinates(0.0,0.0)
        } returns GetWeatherResult.Failure

        Assert.assertEquals(
            getWeatherUseCase(0.0, 0.0),
            GetWeatherResult.Failure
        )
    }

    @Test
    fun `should return expected weather result`() = runTest {
        val mockWeatherRepository = mockk<WeatherRepository>()
        val getWeatherUseCase = GetWeatherUseCase(mockWeatherRepository)
        val expectedWeatherResult = Weather(
            temperature = 0,
            temperatureFeels = -5,
            temperatureMin = -10,
            temperatureMax = 5,
            pressure = 100,
            humidity = 101,
            pressureSeaLevel = 105,
            pressureGroundLevel = 110,
            windSpeed = 3.5,
            windDirectionDegrees = 90,
            windGust = 2.5,
            clouds = 6,
            visibility = 5000,
            city = "city"
        )

        coEvery {
            mockWeatherRepository.getWeatherForCoordinates(0.0,0.0)
        } returns GetWeatherResult.Success(expectedWeatherResult)

        Assert.assertEquals(
            getWeatherUseCase(0.0, 0.0),
            GetWeatherResult.Success(expectedWeatherResult)
        )
    }
}