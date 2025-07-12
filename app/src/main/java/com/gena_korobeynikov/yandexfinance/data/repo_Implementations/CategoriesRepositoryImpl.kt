package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoriesApi,
    private val dispatcher: CoroutineDispatcher
) : CategoriesRepository {

    override suspend fun getCategories(isIncome : Boolean?): List<Category> =
        withContext(dispatcher) {
            if (isIncome != null)
                api.getCategories().filter { it.isIncome == isIncome }.map { it.toDomain() }
            else
                api.getCategories().map { it.toDomain() }
    }


}