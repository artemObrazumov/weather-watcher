package com.main.presentation.di

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
import com.weather.data.remote.repository.WeatherRemoteRepository
import com.weather.data.remote.repository.WeatherRemoteRepositoryImpl
import com.weather.data.repository.WeatherRepositoryImpl
import com.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCityLocalRepository(impl: CityLocalRoomRepositoryImpl): CityLocalRepository

    @Binds
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository

    @Binds
    abstract fun bindWeatherRemoteRepository(impl: WeatherRemoteRepositoryImpl): WeatherRemoteRepository

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}