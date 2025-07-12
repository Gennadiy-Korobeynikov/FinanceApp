package com.gena_korobeynikov.yandexfinance.di.modules

import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.AccountRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.CategoriesRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_Implementations.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindsAccountRepository(repositoryImpl: AccountRepositoryImpl) : AccountRepository

    @Binds
    fun bindsCategoriesRepository(repositoryImpl: CategoriesRepositoryImpl) : CategoriesRepository

    @Binds
    fun bindsTransactionsRepository(repositoryImpl: TransactionsRepositoryImpl) : TransactionsRepository

}