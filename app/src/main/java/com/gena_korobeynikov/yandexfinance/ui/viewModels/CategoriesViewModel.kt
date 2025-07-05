package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.use_cases.categories.GetCategoriesUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.CategoryUi

class CategoriesViewModel(
    private val getCategories : GetCategoriesUseCase =
        GetCategoriesUseCase(TemporaryServiceLocator.categoriesRepository)
) : BaseLoadViewModel<List<Category>, List<CategoryUi>>() {

    fun loadCategories() {
        load(
            mapper = { list -> list.map { it.toUi() } },
            block = { getCategories() }
        )
    }
}