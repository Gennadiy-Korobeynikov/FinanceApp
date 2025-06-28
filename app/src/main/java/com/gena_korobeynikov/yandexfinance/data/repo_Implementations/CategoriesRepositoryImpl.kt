package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.models.Category

class CategoriesRepositoryImpl (
    private val api: CategoriesApi
) : CategoriesRepository {

    override suspend fun getCategories(): List<Category> {
        return api.getCategories().map { it.toDomain() }
    }


}