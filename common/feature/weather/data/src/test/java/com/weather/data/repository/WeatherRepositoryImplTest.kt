package com.weather.data.repository

import com.weather.data.local.location.LocationManager
import com.weather.data.remote.models.RemoteWeather
import com.weather.data.remote.models.RemoteWeatherClouds
import com.weather.data.remote.models.RemoteWeatherMain
import com.weather.data.remote.models.RemoteWeatherWind
import com.weather.data.remote.network.WeatherNetworkService
import com.weather.data.remote.repository.WeatherRemoteRepositoryImpl
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class WeatherRepositoryImplTest {

    @Test
    fun `should return expected remote weather result`() = runTest {
        val mockWeatherNetworkService = mockk<WeatherNetworkService>()
        val mockLocationManager = mockk<LocationManager>()
        val weatherRemoteRepository = WeatherRemoteRepositoryImpl(
            weatherNetworkService = mockWeatherNetworkService,
            locationManager = mockLocationManager
        )
        val weatherRepository = WeatherRepositoryImpl(
            weatherRemoteRepository = weatherRemoteRepository
        )

        val remoteWeather = RemoteWeather(
            main = RemoteWeatherMain(
                temperature = 0.0,
                temperatureFeels = -5.0,
                temperatureMin = -10.0,
                temperatureMax = 5.0,
                pressure = 100,
                humidity = 101,
                pressureSeaLevel = 105,
                pressureGroundLevel = 110,
            ),
            wind = RemoteWeatherWind(
                speed = 3.5,
                direction = 90,
                gust = 2.5
            ),
            clouds = RemoteWeatherClouds(
                all = 6
            ),
            visibility = 5000,
            city = "city"
        )

        coEvery {
            mockWeatherNetworkService.getWeather(0.0, 0.0)
        } returns Response.success(remoteWeather)

        val expectedWeather = Weather(
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

        Assert.assertEquals(
            weatherRepository.getWeatherForCoordinates(0.0, 0.0),
            GetWeatherResult.Success(expectedWeather)
        )
    }
}