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

class ExampleUnitTest {

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
            temperature = 15,
            city = "Moscow"
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