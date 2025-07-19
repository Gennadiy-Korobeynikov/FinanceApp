package com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos

import com.gena_korobeynikov.yandexfinance.data.local.dao.CategoryDao
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.data.mappers.toEntity.toEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesLocalRepositoryImpl @Inject constructor(
    private val dao: CategoryDao,
    private val dispatcher: CoroutineDispatcher
) : CategoriesLocalRepository {

    override suspend fun getCategories(isIncome: Boolean?): List<Category> = withContext(dispatcher) {
        val categories =  dao.getCategories()
        categories.map {it.toDomain()}
    }

    override suspend fun replaceCategories(categories: List<Category>) = withContext(dispatcher) {
        dao.insertCategories(categories.map { it.toEntity() })
    }

}