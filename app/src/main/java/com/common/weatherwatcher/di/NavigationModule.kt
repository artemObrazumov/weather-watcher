package com.common.weatherwatcher.di

import com.main.presentation.navigation.MainFeatureNavigationApi
import com.weatherwatcher.navigation.NavigationItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavigation(): Set<NavigationItemApi> = setOf(
        MainFeatureNavigationApi(),
    )
}