package com.gena_korobeynikov.yandexfinance.di.modules.categoriesModules

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
interface CategoriesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun bindsCategoriesVM(vm : CategoriesViewModel) : ViewModel
}