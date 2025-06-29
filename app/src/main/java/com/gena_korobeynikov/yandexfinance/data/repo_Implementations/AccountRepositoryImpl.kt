package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AccountRepositoryImpl (
    private val api: AccountApi,
    private val dispatcher: CoroutineDispatcher
) : AccountRepository {
    override suspend fun getAccount(accountId: Long): Account =
        withContext(dispatcher) {
            api.getAccount(accountId).toDomain()
    }
}