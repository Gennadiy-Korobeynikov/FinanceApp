package com.gena_korobeynikov.yandexfinance.data.api

import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import retrofit2.http.GET

interface CategoriesApi {
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>
}