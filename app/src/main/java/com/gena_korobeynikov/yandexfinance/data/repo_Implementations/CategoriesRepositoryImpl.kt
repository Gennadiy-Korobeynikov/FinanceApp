package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoriesRepositoryImpl (
    private val api: CategoriesApi,
    private val dispatcher: CoroutineDispatcher
) : CategoriesRepository {

    override suspend fun getCategories(): List<Category> =
        withContext(dispatcher) {
            api.getCategories().map { it.toDomain() }
    }


}