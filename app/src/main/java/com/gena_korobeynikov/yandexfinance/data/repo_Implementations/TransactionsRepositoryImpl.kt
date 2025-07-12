package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsRepositoryImpl @Inject constructor(
    private val api: TransactionsApi,
    private val dispatcher: CoroutineDispatcher
) : TransactionsRepository {

    override suspend fun getTransactionById(transactionId: Long): TransactionReadDto =
        withContext(dispatcher) {
            api.getTransactionById(transactionId)
        }

    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> =
        withContext(dispatcher) {
            api.getTransactionsForAccount(accountId, startDate, endDate).map { it.toDomain() }
    }

    override suspend fun createTransaction(transactionDto: TransactionWriteDto): TransactionReadDto = withContext(dispatcher) {
        api.createTransaction(transactionDto)
    }

    override suspend fun updateTransaction(transactionId: Long, transactionDto: TransactionWriteDto): TransactionReadDto
    = withContext(dispatcher) {
        api.updateTransaction(transactionId, transactionDto)
    }

}