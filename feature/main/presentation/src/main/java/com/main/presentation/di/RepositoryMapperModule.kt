package com.main.presentation.di

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryMapperModule {
//
//    @Binds
//    abstract fun bindCityLocalRepository(cityRepository: CityLocalRoomRepositoryImpl): CityLocalRepository
//
//    @Binds
//    abstract fun bindCityRepository(cityRepository: CityRepositoryImpl): CityRepository
}