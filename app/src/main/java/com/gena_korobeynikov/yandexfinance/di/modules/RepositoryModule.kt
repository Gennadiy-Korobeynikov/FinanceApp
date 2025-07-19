package com.gena_korobeynikov.yandexfinance.di.modules

import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitor
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.AccountLocalRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.account_repos.AccountRemoteRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.account_repos.AccountRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos.CategoriesLocalRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos.CategoriesRemoteRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos.CategoriesRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos.TransactionsLocalRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos.TransactionsRemoteRepositoryImpl
import com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteCategoriesRepository(
        remoteRepo: CategoriesRemoteRepositoryImpl
    ): CategoriesRemoteRepository = remoteRepo

    @Provides
    @Singleton
    fun provideLocalCategoriesRepository(
        localRepo: CategoriesLocalRepositoryImpl
    ): CategoriesLocalRepository = localRepo

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        remote: CategoriesRemoteRepositoryImpl,
        local: CategoriesLocalRepositoryImpl,
        networkMonitor: NetworkMonitor,
    ): CategoriesRepository = CategoriesRepositoryImpl(remote, local, networkMonitor)




    @Provides
    @Singleton
    fun provideRemoteAccountRepository(
        remoteRepo: AccountRemoteRepositoryImpl
    ): AccountRemoteRepository = remoteRepo

    @Provides
    @Singleton
    fun provideLocalAccountRepository(
        localRepo: AccountLocalRepositoryImpl
    ): AccountLocalRepository = localRepo

    @Provides
    @Singleton
    fun provideAccountRepository(
        remote: AccountRemoteRepositoryImpl,
        local: AccountLocalRepositoryImpl,
        networkMonitor: NetworkMonitor
    ): AccountRepository = AccountRepositoryImpl(remote, local, networkMonitor)




    @Provides
    @Singleton
    fun provideRemoteTransactionsRepository(
        remoteRepo: TransactionsRemoteRepositoryImpl
    ): TransactionsRemoteRepository = remoteRepo

    @Provides
    @Singleton
    fun provideLocalTransactionsRepository(
        localRepo: TransactionsLocalRepositoryImpl
    ): TransactionsLocalRepository = localRepo

    @Provides
    @Singleton
    fun provideTransactionsRepository(
        remote: TransactionsRemoteRepository,
        local: TransactionsLocalRepository,
        networkMonitor: NetworkMonitor
    ): TransactionsRepository = TransactionsRepositoryImpl(remote, local, networkMonitor)
}