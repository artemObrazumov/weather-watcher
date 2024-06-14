package com.main.presentation.screens.main.di

import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import com.weather.domain.repository.WeatherRepository
import com.weather.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @MainScreenScope
    @Provides
    fun provideGetCitiesUseCase(cityRepository: CityRepository): GetCitiesUseCase =
        GetCitiesUseCase(cityRepository)

    @MainScreenScope
    @Provides
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepository): GetWeatherUseCase =
        GetWeatherUseCase(weatherRepository)
}