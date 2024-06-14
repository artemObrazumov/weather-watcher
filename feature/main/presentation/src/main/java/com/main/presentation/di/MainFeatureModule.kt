package com.main.presentation.di

import dagger.Module

@Module(
    includes = [RepositoryModule::class]
)
class MainFeatureModule