package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.toDomain
import com.gena_korobeynikov.yandexfinance.domain.Account
import com.gena_korobeynikov.yandexfinance.domain.AccountRepository

class AccountRepositoryImpl (
    private val api: AccountApi
) : AccountRepository {
    override suspend fun getAccount(accountId: Long): Account {
        return api.getAccount(accountId).toDomain()
    }


}