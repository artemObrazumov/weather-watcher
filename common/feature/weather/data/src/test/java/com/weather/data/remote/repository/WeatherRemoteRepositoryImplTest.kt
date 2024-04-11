package com.weather.data.remote.repository

import com.weather.data.local.location.LocationManager
import com.weather.data.remote.models.RemoteWeather
import com.weather.data.remote.models.RemoteWeatherClouds
import com.weather.data.remote.models.RemoteWeatherMain
import com.weather.data.remote.models.RemoteWeatherWind
import com.weather.data.remote.models.result.RemoteWeatherResult
import com.weather.data.remote.network.WeatherNetworkService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class WeatherRemoteRepositoryImplTest {

    @Test
    fun `should return failure on exception`() = runTest {
        val mockWeatherNetworkService = mockk<WeatherNetworkService>()
        val mockLocationManager = mockk<LocationManager>()
        val weatherRemoteRepository = WeatherRemoteRepositoryImpl(
            weatherNetworkService = mockWeatherNetworkService,
            locationManager = mockLocationManager
        )

        coEvery {
            mockWeatherNetworkService.getWeather(0.0, 0.0)
        } returns Response.error(404, ResponseBody.create(null, ""))

        Assert.assertEquals(
            weatherRemoteRepository.getWeather(0.0, 0.0),
            RemoteWeatherResult.Failure
        )
    }

    @Test
    fun `should return expected weather result`() = runTest {
        val mockWeatherNetworkService = mockk<WeatherNetworkService>()
        val mockLocationManager = mockk<LocationManager>()
        val weatherRemoteRepository = WeatherRemoteRepositoryImpl(
            weatherNetworkService = mockWeatherNetworkService,
            locationManager = mockLocationManager
        )

        val expectedResult = RemoteWeather(
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
        } returns Response.success(expectedResult)

        Assert.assertEquals(
            weatherRemoteRepository.getWeather(0.0, 0.0),
            RemoteWeatherResult.Success(expectedResult)
        )
    }
}