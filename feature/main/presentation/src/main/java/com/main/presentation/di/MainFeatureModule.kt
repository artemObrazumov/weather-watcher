package com.main.presentation.di

import com.main.presentation.screens.main.di.MainScreenComponent
import com.main.presentation.screens.main.di.MainScreenModule
import dagger.Module

@Module(
    subcomponents = [MainScreenComponent::class],
    includes = [RepositoryMapperModule::class, RepositoryModule::class]
)
class MainFeatureModule