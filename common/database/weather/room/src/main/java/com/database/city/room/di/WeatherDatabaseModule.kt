package com.database.city.room.di

import android.content.Context
import com.database.city.room.WeatherLogDatabase
import com.database.city.room.dao.WeatherLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherDatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherLogDatabase(@ApplicationContext context: Context): WeatherLogDatabase =
        WeatherLogDatabase.create(context)

    @Singleton
    @Provides
    fun provideWeatherLogDao(database: WeatherLogDatabase): WeatherLogDao =
        database.weatherLogDao
}