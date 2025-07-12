package com.gena_korobeynikov.yandexfinance.di.components

import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.di.modules.accountModules.AccountViewModelModule
import com.gena_korobeynikov.yandexfinance.di.scopes.AccountScope
import com.gena_korobeynikov.yandexfinance.ui.viewModels.account.EditAccountViewModel
import dagger.Subcomponent


@AccountScope
@Subcomponent(
    modules = [
        AccountViewModelModule::class
    ]
)
interface AccountComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountComponent
    }
    fun editAccountViewModelFactory(): EditAccountViewModel.Factory
    fun viewModelFactory() : ViewModelProvider.Factory
}