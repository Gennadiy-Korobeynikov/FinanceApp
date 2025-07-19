package com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRemoteRepositoryImpl @Inject constructor(
    private val api: CategoriesApi,
    private val dispatcher: CoroutineDispatcher
) : CategoriesRemoteRepository {

    override suspend fun getCategories(isIncome : Boolean?): List<Category> =
        withContext(dispatcher) {
            if (isIncome != null)
                api.getCategories().filter { it.isIncome == isIncome }.map { it.toDomain() }
            else
                api.getCategories().map { it.toDomain() }
        }


}