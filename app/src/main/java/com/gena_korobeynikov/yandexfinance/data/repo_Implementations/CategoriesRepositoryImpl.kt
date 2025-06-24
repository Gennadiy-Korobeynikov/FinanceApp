package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.toDomain
import com.gena_korobeynikov.yandexfinance.domain.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.Category

class CategoriesRepositoryImpl (
    private val api: CategoriesApi
) : CategoriesRepository {

    override suspend fun getCategories(): List<Category> {
        return api.getCategories().map { it.toDomain() }
    }


}