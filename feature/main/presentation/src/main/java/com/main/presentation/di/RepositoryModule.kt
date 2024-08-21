package com.main.presentation.di

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
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
}