package com.gena_korobeynikov.yandexfinance.domain.repos

import com.gena_korobeynikov.yandexfinance.domain.models.Category

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}