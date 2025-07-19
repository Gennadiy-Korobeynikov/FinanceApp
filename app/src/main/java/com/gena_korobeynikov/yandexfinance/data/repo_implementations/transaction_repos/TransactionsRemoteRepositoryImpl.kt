package com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos

import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.data.mappers.toDto.toWriteDto
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsRemoteRepositoryImpl @Inject constructor(
    private val api: TransactionsApi,
    private val dispatcher: CoroutineDispatcher
) : TransactionsRemoteRepository {

    override suspend fun getTransactionById(id: Long): TransactionReadDto =
        withContext(dispatcher) {
            api.getTransactionById(id)
        }

    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> =
        withContext(dispatcher) {
            api.getTransactionsForAccount(accountId, startDate, endDate).map { it.toDomain() }
    }

    override suspend fun pushTransactions(transactions: List<Transaction>) {
        for (transaction in transactions) {
            val response = if (transaction.id < 0) {
                // Новая транзакция
                createTransaction(transaction.toWriteDto())
            } else {
                // Существующая, была отредактирована
                updateTransaction(transaction.id, transaction.toWriteDto())
            }
        }
    }

    override suspend fun createTransaction(dto: TransactionWriteDto): Unit = withContext(dispatcher) {
        api.createTransaction(dto)
    }

    override suspend fun updateTransaction(id: Long, dto: TransactionWriteDto): Unit = withContext(dispatcher) {
        api.updateTransaction(id, dto)
    }

}