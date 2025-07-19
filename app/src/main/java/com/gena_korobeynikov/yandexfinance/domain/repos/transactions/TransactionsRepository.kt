package com.gena_korobeynikov.yandexfinance.domain.repos.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction

interface TransactionsRepository {
    suspend fun getTransactionById(transactionId: Long): TransactionReadDto
    suspend fun getTransactionsForPeriod(accountId: Long,startDate : String?, endDate : String? ): List<Transaction>
    suspend fun createTransaction(transactionDto: TransactionWriteDto)
    suspend fun updateTransaction(transactionId : Long, transactionDto: TransactionWriteDto)
}