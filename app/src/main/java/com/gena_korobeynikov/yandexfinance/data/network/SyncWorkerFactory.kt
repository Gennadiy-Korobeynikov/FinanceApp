package com.gena_korobeynikov.yandexfinance.data.network

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.gena_korobeynikov.yandexfinance.domain.SyncManager
import javax.inject.Inject

class SyncWorkerFactory @Inject constructor(
    private val syncManager: SyncManager
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return if (workerClassName == SyncWorker::class.qualifiedName) {
            SyncWorker(appContext, workerParameters, syncManager)
        } else {
            null
        }
    }
}