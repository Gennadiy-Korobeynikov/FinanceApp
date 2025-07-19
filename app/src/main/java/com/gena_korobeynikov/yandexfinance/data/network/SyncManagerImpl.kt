package com.gena_korobeynikov.yandexfinance.data.network

import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.mappers.toDto.toDto
import com.gena_korobeynikov.yandexfinance.domain.SyncManager
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRemoteRepository
import javax.inject.Inject

class SyncManagerImpl @Inject constructor(
    private val remoteTransactionsRepo: TransactionsRemoteRepository,
    private val localTransactionsRepo: TransactionsLocalRepository,

    private val remoteCategoriesRepo: CategoriesRemoteRepository,
    private val localCategoriesRepo: CategoriesLocalRepository,

    private  val remoteAccountRepo: AccountRemoteRepository,
    private val localAccountRepo: AccountLocalRepository,

    private val preferences: SyncPreferences,
    private val networkMonitor: NetworkMonitor) : SyncManager
{
    override suspend fun syncIfNeeded() {
        if (!networkMonitor.isConnected()) return

        val lastSync = preferences.getLastSyncTimestamp()


        val allCategories = remoteCategoriesRepo.getCategories()
        val account = remoteAccountRepo.getAccount(ACCOUNT_ID)
        if (allCategories.isNotEmpty()) {
            localCategoriesRepo.replaceCategories(allCategories)
        }
        localAccountRepo.upsertAccount(account.toDto())

        val locallyChanged = localTransactionsRepo.getTransactionsToPush(lastSync)
        if (locallyChanged.isNotEmpty()) {
            remoteTransactionsRepo.pushTransactions(locallyChanged)
        }

        val endDate = java.time.LocalDate.now()
        val startDate = endDate.minusMonths(3) // Синхронизируем последние 3 месяца
        val updated = remoteTransactionsRepo.getTransactionsForPeriod(
            ACCOUNT_ID,
            startDate.toString(),
            endDate.toString())

        localTransactionsRepo.replaceAll(updated)

        preferences.setLastSyncTimestamp(java.time.Instant.now().toEpochMilli())
    }
}