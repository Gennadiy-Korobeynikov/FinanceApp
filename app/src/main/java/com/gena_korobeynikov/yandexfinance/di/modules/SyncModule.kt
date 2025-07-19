package com.gena_korobeynikov.yandexfinance.di.modules

import androidx.work.WorkerFactory
import com.gena_korobeynikov.yandexfinance.data.network.SyncManagerImpl
import com.gena_korobeynikov.yandexfinance.data.network.SyncWorkerFactory
import com.gena_korobeynikov.yandexfinance.domain.SyncManager
import dagger.Binds
import dagger.Module

@Module
interface SyncModule {
    @Binds
    fun bindsSyncManager(syncManager: SyncManagerImpl): SyncManager

    @Binds
    fun bindWorkerFactory(factory: SyncWorkerFactory): WorkerFactory
}