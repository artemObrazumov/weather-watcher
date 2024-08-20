package com.main.presentation.di

import com.city.data.local.models.City
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
import com.database.city.room.CityDatabase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @MainFeatureScope
    @Provides
    fun provideLocalRoomRepositoryImpl(database: CityDatabase): CityLocalRoomRepositoryImpl =
        CityLocalRoomRepositoryImpl(database)

    @MainFeatureScope
    @Provides
    fun provideCityRepository(
        cityLocalRepository: CityLocalRepository
    ): CityRepository = CityRepositoryImpl(cityLocalRepository)
}