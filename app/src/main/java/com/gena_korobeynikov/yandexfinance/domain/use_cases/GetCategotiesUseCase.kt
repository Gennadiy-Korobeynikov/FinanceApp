package com.gena_korobeynikov.yandexfinance.domain.use_cases

import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.repos.CategoriesRepository

class GetCategoriesUseCase(
    private val repository: CategoriesRepository,
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}