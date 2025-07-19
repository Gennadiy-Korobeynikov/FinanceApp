package com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitor
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRepository
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val remoteRepo: TransactionsRemoteRepository,
    private val localRepo: TransactionsLocalRepository,
    private val networkMonitor: NetworkMonitor
) : TransactionsRepository {

    override suspend fun getTransactionById(transactionId: Long) : TransactionReadDto {
        return if (networkMonitor.isConnected()) {
            remoteRepo.getTransactionById(transactionId)
        } else {
            localRepo.getTransactionById(transactionId)
        }
    }

    override suspend fun getTransactionsForPeriod(
        accountId: Long,
        startDate: String?,
        endDate: String?,
    ): List<Transaction> {
        return if (networkMonitor.isConnected()) {
            remoteRepo.getTransactionsForPeriod(
                accountId,
                startDate,
                endDate
            )
        } else {
            localRepo.getTransactionsForPeriod(
                accountId,
                startDate,
                endDate
            )
        }
    }

    override suspend fun createTransaction(transactionDto: TransactionWriteDto) {
        return if (networkMonitor.isConnected()) {
            remoteRepo.createTransaction(transactionDto)
        } else {
            localRepo.insertTransaction(transactionDto)
        }
    }

    override suspend fun updateTransaction(
        transactionId: Long,
        transactionDto: TransactionWriteDto,
    ) {
        return if (networkMonitor.isConnected()) {
            remoteRepo.updateTransaction(transactionId, transactionDto)
        } else {
            localRepo.updateTransaction(transactionId, transactionDto)
        }
    }
}
