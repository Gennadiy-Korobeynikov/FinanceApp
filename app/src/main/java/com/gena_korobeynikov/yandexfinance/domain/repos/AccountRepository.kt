package com.gena_korobeynikov.yandexfinance.domain.repos

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account

interface
AccountRepository {
    suspend fun getAccount(accountId: Long): Account
    suspend fun updateAccount(account: AccountDto) : Account
}