package com.main.presentation.screens.main.di

import com.city.data.local.models.City
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.repository.CityLocalRepository
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.models.city.CityPagingItem
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import com.main.presentation.di.CityRepoTest
import com.main.presentation.di.MainFeatureScope
import com.weather.domain.repository.WeatherRepository
import com.weather.domain.usecase.GetWeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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