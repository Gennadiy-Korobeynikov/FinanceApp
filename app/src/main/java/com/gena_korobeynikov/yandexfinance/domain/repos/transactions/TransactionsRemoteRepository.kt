package com.gena_korobeynikov.yandexfinance.domain.repos.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction

interface TransactionsRemoteRepository {
    suspend fun pushTransactions(transactions: List<Transaction>)
    suspend fun createTransaction(dto: TransactionWriteDto)
    suspend fun updateTransaction(id: Long, dto: TransactionWriteDto)
    suspend fun getTransactionById(id: Long): TransactionReadDto
    suspend fun getTransactionsForPeriod(accountId: Long, startDate: String?, endDate: String?): List<Transaction>
}