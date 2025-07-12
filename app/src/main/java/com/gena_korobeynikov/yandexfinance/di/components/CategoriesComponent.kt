package com.gena_korobeynikov.yandexfinance.di.components

import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.di.modules.categoriesModules.CategoriesViewModelModule
import com.gena_korobeynikov.yandexfinance.di.scopes.CategoriesScope
import dagger.Subcomponent


@CategoriesScope
@Subcomponent(
    modules = [
        CategoriesViewModelModule::class
    ]
)
interface CategoriesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CategoriesComponent
    }

    fun viewModelFactory() : ViewModelProvider.Factory
}