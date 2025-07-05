package com.gena_korobeynikov.yandexfinance.domain.use_cases.categories

import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository

class GetCategoriesUseCase(
    private val repository: CategoriesRepository,
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}