package com.gena_korobeynikov.yandexfinance

import android.app.Application
import androidx.work.Configuration
import com.gena_korobeynikov.yandexfinance.data.network.SyncScheduler
import com.gena_korobeynikov.yandexfinance.data.network.SyncWorkerFactory
import com.gena_korobeynikov.yandexfinance.di.components.AppComponent
import com.gena_korobeynikov.yandexfinance.di.components.DaggerAppComponent
import com.gena_korobeynikov.yandexfinance.domain.SyncManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class YandexFinanceApp : Application(), Configuration.Provider {
    lateinit var appComponent: AppComponent
        private set

    @Inject
    lateinit var syncWorkerFactory: SyncWorkerFactory

    @Inject
    lateinit var syncManager: SyncManager

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        syncWorkerFactory = appComponent.getSyncWorkerFactory()
        syncManager = appComponent.getSyncManager()
        SyncScheduler.schedule(applicationContext)
        SyncScheduler.runOnce(applicationContext)

        CoroutineScope(Dispatchers.Default).launch {
            syncManager.syncIfNeeded()
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(syncWorkerFactory)
            .build()


}