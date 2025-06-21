package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionsApi {
    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForAccount(
        @Path("accountId") accountId: Long,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionDto>
}