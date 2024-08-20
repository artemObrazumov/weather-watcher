package com.database.city.room.di

import android.content.Context
import com.database.city.room.CityDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CityDatabaseModule {

    @Singleton
    @Provides
    fun provideCityLocalDatabase(context: Context): CityDatabase =
        CityDatabase.create(context)
}