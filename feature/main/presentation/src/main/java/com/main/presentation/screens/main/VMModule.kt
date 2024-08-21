package com.main.presentation.screens.main

import android.content.Context
import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import com.database.city.room.CityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VMModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): CityDatabase =
        CityDatabase.create(context)

//    @Singleton
//    @Provides
//    fun provideCityLocalRepository(db: CityDatabase): CityLocalRepository =
//        CityLocalRoomRepositoryImpl(db)

    @Singleton
    @Provides
    fun provideCityRepository(cityLocalRepository: CityLocalRepository): CityRepository =
        CityRepositoryImpl(cityLocalRepository)

    @Singleton
    @Provides
    fun provideGetCitiesUseCase(cityRepository: CityRepository): GetCitiesUseCase =
        GetCitiesUseCase(cityRepository)
}