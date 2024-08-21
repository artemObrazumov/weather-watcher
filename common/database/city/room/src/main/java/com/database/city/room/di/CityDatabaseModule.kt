package com.database.city.room.di

import android.content.Context
import com.database.city.room.CityDatabase
import com.database.city.room.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CityDatabaseModule {

    @Singleton
    @Provides
    fun provideCityDatabase(@ApplicationContext context: Context): CityDatabase =
        CityDatabase.create(context)

    @Singleton
    @Provides
    fun provideCityDao(database: CityDatabase): CityDao =
        database.cityDao
}