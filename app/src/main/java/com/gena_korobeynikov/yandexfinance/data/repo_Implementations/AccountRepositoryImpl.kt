package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository

class AccountRepositoryImpl (
    private val api: AccountApi
) : AccountRepository {
    override suspend fun getAccount(accountId: Long): Account {
        return api.getAccount(accountId).toDomain()
    }


}