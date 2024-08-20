package com.common.weatherwatcher

import android.app.Application
import android.content.Context
import com.common.weatherwatcher.di.application.AppComponent
import com.common.weatherwatcher.di.application.DaggerAppComponent
import com.main.presentation.di.MainFeatureComponent
import com.main.presentation.di.MainFeatureComponentProvider

class WeatherWatcherApp: Application(), MainFeatureComponentProvider  {

    private var _appComponent: AppComponent? = null
    val appComponent get() = checkNotNull(_appComponent) {""}

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun provideMainFeatureComponent(): MainFeatureComponent {
        return appComponent.mainFeatureComponentFactory.create()
    }
}

fun Context.getAppComponent(): AppComponent = when (this) {
        is WeatherWatcherApp -> appComponent
        else -> applicationContext.getAppComponent()
    }