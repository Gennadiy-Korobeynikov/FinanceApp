package com.gena_korobeynikov.yandexfinance.domain

import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.toDomain

class AccountRepositoryImpl (
    private val api: AccountApi
) : AccountRepository {
    override suspend fun getAccount(accountId: Long): Account {
        return api.getAccount(accountId).toDomain()
    }


}