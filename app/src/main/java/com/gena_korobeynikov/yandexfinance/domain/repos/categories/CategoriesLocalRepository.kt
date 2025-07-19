package com.gena_korobeynikov.yandexfinance.domain.repos.categories

import com.gena_korobeynikov.yandexfinance.domain.models.Category

interface CategoriesLocalRepository {
    suspend fun getCategories(isIncome : Boolean? = null): List<Category>
    suspend fun replaceCategories(categories: List<Category>)
}