package com.gena_korobeynikov.yandexfinance.data.local

interface OfflineIdProvider {
    suspend fun getNextId(): Long
}