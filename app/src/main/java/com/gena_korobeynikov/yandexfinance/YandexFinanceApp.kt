package com.gena_korobeynikov.yandexfinance

import android.app.Application
import com.gena_korobeynikov.yandexfinance.di.components.AppComponent
import com.gena_korobeynikov.yandexfinance.di.components.DaggerAppComponent

class YandexFinanceApp : Application() {
    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
 }