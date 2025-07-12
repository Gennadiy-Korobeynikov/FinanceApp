package com.gena_korobeynikov.yandexfinance.di.components

import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.di.modules.transactionsModules.TransactionsViewModelModule
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.EditTransactionViewModel
import dagger.Subcomponent


@TransactionsScope
@Subcomponent(
    modules = [
        TransactionsViewModelModule::class
    ]
)
interface TransactionsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TransactionsComponent
    }

    fun editTransactionViewModelFactory(): EditTransactionViewModel.Factory

    fun viewModelFactory() : ViewModelProvider.Factory
}