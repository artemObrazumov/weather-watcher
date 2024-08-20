package com.common.weatherwatcher.di.application

import android.content.Context
import com.common.weatherwatcher.di.navigation.NavigationComponent
import com.main.presentation.di.MainFeatureComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    val navigationFactory: NavigationComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    val mainFeatureComponentFactory: MainFeatureComponent.Factory
}