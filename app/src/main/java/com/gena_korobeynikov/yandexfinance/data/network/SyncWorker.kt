package com.gena_korobeynikov.yandexfinance.data.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gena_korobeynikov.yandexfinance.domain.SyncManager

class SyncWorker (
    appContext: Context,
    workerParams: WorkerParameters,
    private val syncManager: SyncManager
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            syncManager.syncIfNeeded()
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}