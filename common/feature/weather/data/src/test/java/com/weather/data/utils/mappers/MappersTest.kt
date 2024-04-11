package com.weather.data.utils.mappers

import com.weather.data.remote.models.RemoteWeather
import com.weather.data.remote.models.RemoteWeatherClouds
import com.weather.data.remote.models.RemoteWeatherMain
import com.weather.data.remote.models.RemoteWeatherWind
import com.weather.domain.models.weather.Weather
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MappersTest {

    @Test
    fun `should return expected domain weather`() = runTest {
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

        val expectedResult = Weather(
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
            remoteWeather.toDomain(),
            expectedResult
        )
    }
}