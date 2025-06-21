package com.gena_korobeynikov.yandexfinance.domain

interface AccountRepository {
    suspend fun getAccount(accountId: Long): Account
}