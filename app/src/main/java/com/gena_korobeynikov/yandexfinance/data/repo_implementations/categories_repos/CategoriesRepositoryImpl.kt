package com.gena_korobeynikov.yandexfinance.data.repo_implementations.categories_repos

import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitor
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesLocalRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRemoteRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.categories.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val remoteRepo: CategoriesRemoteRepository,
    private val localRepo: CategoriesLocalRepository,
    private val networkMonitor: NetworkMonitor
) : CategoriesRepository {

    override suspend fun getCategories(isIncome: Boolean?): List<Category> {
        return if (networkMonitor.isConnected()) {
            remoteRepo.getCategories(isIncome)
        } else {
            localRepo.getCategories(isIncome)
        }
    }
}
