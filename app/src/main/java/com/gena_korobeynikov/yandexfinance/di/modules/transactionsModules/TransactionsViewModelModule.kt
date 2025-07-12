package com.gena_korobeynikov.yandexfinance.di.modules.transactionsModules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.di.DaggerViewModelFactory
import com.gena_korobeynikov.yandexfinance.di.ViewModelKey
import com.gena_korobeynikov.yandexfinance.ui.viewModels.account.AccountViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.categories.CategoriesViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.CreateTransactionViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.HistoryViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.TodayTransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TransactionsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun bindsHistoryVM(vm : HistoryViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TodayTransactionsViewModel::class)
    fun bindsTodayTransactionsVM(vm : TodayTransactionsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTransactionViewModel::class)
    fun bindsCreateTransactionVM(vm : CreateTransactionViewModel) : ViewModel
}