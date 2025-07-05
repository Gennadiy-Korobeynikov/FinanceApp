package com.gena_korobeynikov.yandexfinance.di.modules

import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface CommonViewModelModule {
    @Binds
    fun bindsDaggerViewModelFactory( factory: DaggerViewModelFactory): ViewModelProvider.Factory
}