package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.UpdatingAccountDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

const val ACCOUNT_ID = 1L

interface AccountApi {
    @GET("accounts/{id}")
    suspend fun getAccount(
        @Path("id") accountId: Long
    ): AccountDto


    @PUT("accounts/{id}")
    suspend fun updateAccountById(
        @Path("id") id: Long,
        @Body accountDto: UpdatingAccountDto
    ): AccountDto
}