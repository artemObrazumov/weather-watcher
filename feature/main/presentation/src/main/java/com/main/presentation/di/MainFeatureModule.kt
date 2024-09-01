package com.main.presentation.di

import com.main.presentation.navigation.MainFeatureNavigationApi
import com.weatherwatcher.navigation.NavigationItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainFeatureModule {

    @Provides
    @Singleton
    fun provideMainFeatureNavigationApi(): NavigationItemApi = MainFeatureNavigationApi()
}