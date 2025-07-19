package com.gena_korobeynikov.yandexfinance.domain

interface SyncManager {
    suspend fun syncIfNeeded()
}