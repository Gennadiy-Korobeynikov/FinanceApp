package com.gena_korobeynikov.yandexfinance.data.repo_implementations.transaction_repos

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.local.OfflineIdProvider
import com.gena_korobeynikov.yandexfinance.data.local.dao.TransactionDao
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.data.mappers.toDto.toReadDto
import com.gena_korobeynikov.yandexfinance.data.mappers.toEntity.toEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsLocalRepositoryImpl @Inject constructor(
    private val dao : TransactionDao,
    private val dispatcher: CoroutineDispatcher,
    private val offlineIdProvider : OfflineIdProvider
) : TransactionsLocalRepository {

    override suspend fun getTransactionById(id: Long): TransactionReadDto =
        withContext(dispatcher) {
            val transaction = dao.getTransactionById("test") //TODO replcae
            transaction?.toReadDto()
                ?: throw NoSuchElementException("Transaction with id $id not found")
        }

    override suspend fun getTransactionsForPeriod(accountId: Long, startDate : String?, endDate : String?): List<Transaction> =
        withContext(dispatcher) {

            val zoneId = java.time.ZoneId.systemDefault()

            var startDateWithDefault = startDate
            var endDateWithDefault = endDate

            if (startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty()) {
                startDateWithDefault = LocalDate.now().withDayOfMonth(1).toString() // Начало текущего месяца
                endDateWithDefault = LocalDate.now().toString() // Сегодняшний день
            }

            dao.getTransactionsForPeriod(
                accountId,

                LocalDate.parse(startDateWithDefault)
                    .atStartOfDay(zoneId)
                    .toInstant()
                    .toEpochMilli(),

                LocalDate.parse(endDateWithDefault)
                    .atStartOfDay(zoneId)
                    .toInstant()
                    .toEpochMilli()
            ).map { it.toDomain() }    }


    override suspend fun getTransactionsToPush(timestamp: Long): List<Transaction> {
        return dao.getChangedTransactionsSince(timestamp).map { it.toDomain() }
    }

    override suspend fun replaceAll(transactions: List<Transaction>) {
        dao.replaceAllTransactions(transactions.map { it.toEntity() })
    }


    override suspend fun insertTransaction(transactionDto: TransactionWriteDto) = withContext(dispatcher) {
        dao.createTransaction(transactionDto.toEntity(offlineIdProvider.getNextId()))
    }

    override suspend fun updateTransaction(transactionId: Long, transactionDto: TransactionWriteDto)
    = withContext(dispatcher) {
        dao.updateTransaction( transactionDto.toEntity(transactionId))
    }


}