package com.gena_korobeynikov.yandexfinance.di

import com.gena_korobeynikov.yandexfinance.common.NetworkModule
import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.AccountRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.CategoriesRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import kotlinx.coroutines.Dispatchers

// Временно, до дз по внедрению DI
object TemporaryServiceLocator {

    val transactionsRepository: TransactionsRepository by lazy {
        TransactionsRepositoryImpl(NetworkModule.transactionsApi, Dispatchers.IO)
    }

    val categoriesRepository: CategoriesRepository by lazy {
        CategoriesRepositoryImpl(NetworkModule.categoryApi, Dispatchers.IO)
    }

    val accountRepository: AccountRepository by lazy {
        AccountRepositoryImpl(NetworkModule.accountApi, Dispatchers.IO)
    }


}