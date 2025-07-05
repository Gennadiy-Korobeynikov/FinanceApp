package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionsApi {

    @GET("transactions/{id}")
    suspend fun getTransactionById(
        @Path("id") id: Long
    ): TransactionReadDto

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForAccount(
        @Path("accountId") accountId: Long,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionReadDto>

    @POST("transactions")
    suspend fun createTransaction(
        @Body transactionDto: TransactionWriteDto
    ) : TransactionReadDto

    @PUT("transactions/{id}")
    suspend fun updateTransaction(
        @Path("id") id: Long,
        @Body transactionDto: TransactionWriteDto
    ): TransactionReadDto
}