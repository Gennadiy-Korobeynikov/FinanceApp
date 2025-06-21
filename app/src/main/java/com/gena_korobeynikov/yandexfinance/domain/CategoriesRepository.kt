package com.gena_korobeynikov.yandexfinance.domain

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}