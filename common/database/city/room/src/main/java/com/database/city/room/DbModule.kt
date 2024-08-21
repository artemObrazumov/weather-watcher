package com.database.city.room

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

//    @Singleton
//    @Provides
//    fun provideDb(@ApplicationContext context: Context) = CityDatabase.create(context)
}