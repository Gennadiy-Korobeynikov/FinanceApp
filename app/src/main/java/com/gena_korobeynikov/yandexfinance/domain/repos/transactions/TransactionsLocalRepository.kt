package com.gena_korobeynikov.yandexfinance.domain.repos.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction

interface  TransactionsLocalRepository {
    suspend fun getTransactionsToPush(timestamp: Long): List<Transaction>
    suspend fun replaceAll(transactions: List<Transaction>)
    suspend fun insertTransaction(transactionDto: TransactionWriteDto)
    suspend fun updateTransaction(transactionId: Long, transactionDto: TransactionWriteDto)
    suspend fun getTransactionById(id: Long): TransactionReadDto
    suspend fun getTransactionsForPeriod(accountId: Long, startDate: String?, endDate: String?): List<Transaction>
}