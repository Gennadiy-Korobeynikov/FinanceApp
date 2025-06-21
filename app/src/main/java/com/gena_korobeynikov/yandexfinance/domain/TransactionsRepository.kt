package com.gena_korobeynikov.yandexfinance.domain

interface TransactionsRepository {
    suspend fun getTransactionsForPeriod(accountId: Long,startDate : String?, endDate : String? ): List<Transaction>
}