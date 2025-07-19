package com.gena_korobeynikov.yandexfinance.domain.repos.account

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account

interface AccountRepository {
    suspend fun getAccount(accountId: Long): Account
    suspend fun updateAccount(accountDto: AccountDto) : Any
}