package com.gena_korobeynikov.yandexfinance.data.repo_implementations.account_repos

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitor
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val remoteRepo: AccountRemoteRepository,
    private val localRepo: AccountLocalRepository,
    private val networkMonitor: NetworkMonitor
) : AccountRepository {
    override suspend fun getAccount(accountId: Long): Account {
        return if (networkMonitor.isConnected()) {
            remoteRepo.getAccount(accountId)
        } else {
            localRepo.getAccount(accountId)
        }
    }

    override suspend fun updateAccount(accountDto: AccountDto): Any {
        return if (networkMonitor.isConnected()) {
            remoteRepo.updateAccount(accountDto)
        } else {
            localRepo.upsertAccount(accountDto)
        }
    }

}
