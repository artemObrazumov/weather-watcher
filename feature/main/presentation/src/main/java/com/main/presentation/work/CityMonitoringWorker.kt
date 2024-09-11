package com.main.presentation.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.city.domain.models.result.CityGetResult
import com.city.domain.usecase.GetCityUseCase
import com.weather.data.utils.mappers.toWeatherLog
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.WeatherLog
import com.weather.domain.models.weather.WeatherLogEntry
import com.weather.domain.usecase.GetWeatherUseCase
import com.weather.domain.usecase.LogWeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.Calendar

@HiltWorker
class CityMonitoringWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCityUseCase: GetCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val logWeatherUseCase: LogWeatherUseCase
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val cityId = inputData.getInt(WORKER_CITY_ID, -1)
            val cityGetResult = getCityUseCase.invoke(cityId)
            when (cityGetResult) {
                is CityGetResult.Failure -> {
                    WeatherLogEntry.UnsuccessfulLog(
                        cityId = cityId,
                        time = LocalDateTime.now(),
                        errorMessage = cityGetResult.errorMessage
                    )
                    Result.success()
                }

                is CityGetResult.Success -> {
                    val city = cityGetResult.cityFlow.first()
                    val weatherGetResult = getWeatherUseCase.invoke(
                        city.latitude, city.longitude
                    )
                    logWeatherUseCase.invoke(
                        when (weatherGetResult) {
                            is GetWeatherResult.Failure -> {
                                WeatherLogEntry.UnsuccessfulLog(
                                    cityId = cityId,
                                    time = LocalDateTime.now(),
                                    errorMessage = weatherGetResult.errorMessage
                                )
                            }
                            is GetWeatherResult.Success -> {
                                WeatherLogEntry.SuccessfulLog(
                                    weatherLog = weatherGetResult.weather
                                        .toWeatherLog(
                                            time = LocalDateTime.now(),
                                            cityId = cityId
                                        )
                                )
                            }
                        }
                    )
                    Result.success()
                }
            }
        }
    }

    companion object {
        const val WORKER_CITY_ID = "worker_city_id"
    }
}