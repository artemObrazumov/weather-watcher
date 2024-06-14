package com.main.presentation.di

import android.content.Context
import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.repository.CityRepositoryImpl
import com.city.domain.repository.CityRepository
import com.database.city.room.CityDatabase
import com.main.presentation.screens.main.di.MainScreenScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCityLocalDatabase(context: Context): CityDatabase =
        CityDatabase.create(context)

    @MainScreenScope
    @Provides
    fun provideLocalRoomRepositoryImpl(database: CityDatabase): CityLocalRoomRepositoryImpl =
        CityLocalRoomRepositoryImpl(database)

    @MainFeatureScope
    @Provides
    fun provideCityRepository(
        localRoomRepositoryImpl: CityLocalRoomRepositoryImpl
    ): CityRepository = CityRepositoryImpl(localRoomRepositoryImpl)
}