package com.gena_korobeynikov.yandexfinance.domain

import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.toDomain

class TransactionsRepositoryImpl (
    private val api: TransactionsApi
) : TransactionsRepository {
    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> {
        return api.getTransactionsForAccount(accountId, startDate, endDate).map { it.toDomain() }
    }


}