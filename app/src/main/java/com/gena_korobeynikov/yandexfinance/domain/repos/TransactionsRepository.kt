package com.gena_korobeynikov.yandexfinance.domain.repos

import com.gena_korobeynikov.yandexfinance.domain.models.Transaction

interface TransactionsRepository {
    suspend fun getTransactionsForPeriod(accountId: Long,startDate : String?, endDate : String? ): List<Transaction>
}