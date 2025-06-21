package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("accounts/{id}")
    suspend fun getAccount(
        @Path("id") accountId: Long
    ): AccountDto
}