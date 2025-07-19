package com.gena_korobeynikov.yandexfinance.domain.repos.account

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account

interface AccountLocalRepository {
    suspend fun getAccount(id : Long) : Account
    suspend fun upsertAccount(account: AccountDto): Any
}