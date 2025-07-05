package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TransactionsRepositoryImpl (
    private val api: TransactionsApi,
    private val dispatcher: CoroutineDispatcher
) : TransactionsRepository {
    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> =
        withContext(dispatcher) {
            api.getTransactionsForAccount(accountId, startDate, endDate).map { it.toDomain() }
    }

}