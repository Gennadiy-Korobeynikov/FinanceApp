package com.gena_korobeynikov.yandexfinance.domain.repos.categories

import com.gena_korobeynikov.yandexfinance.domain.models.Category

interface CategoriesRemoteRepository {
    suspend fun getCategories(isIncome : Boolean? = null): List<Category>
}