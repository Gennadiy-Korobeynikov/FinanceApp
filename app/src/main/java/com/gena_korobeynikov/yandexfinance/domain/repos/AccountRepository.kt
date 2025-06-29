package com.gena_korobeynikov.yandexfinance.domain.repos

import com.gena_korobeynikov.yandexfinance.domain.models.Account

interface
AccountRepository {
    suspend fun getAccount(accountId: Long): Account
}