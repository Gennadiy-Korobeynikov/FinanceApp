package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.toDomain
import com.gena_korobeynikov.yandexfinance.domain.Transaction
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepository

class TransactionsRepositoryImpl (
    private val api: TransactionsApi
) : TransactionsRepository {
    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> {
        return api.getTransactionsForAccount(accountId, startDate, endDate).map { it.toDomain() }
    }


}