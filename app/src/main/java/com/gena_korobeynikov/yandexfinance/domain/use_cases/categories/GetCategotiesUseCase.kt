package com.gena_korobeynikov.yandexfinance.domain.use_cases.categories

import com.gena_korobeynikov.yandexfinance.di.scopes.CategoriesScope
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository,
) {
    suspend operator fun invoke(isIncome : Boolean? = null): List<Category> {
        return repository.getCategories(isIncome)
    }
}