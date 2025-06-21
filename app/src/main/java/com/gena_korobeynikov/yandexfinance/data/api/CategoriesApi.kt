package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApi {
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>
}