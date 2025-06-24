package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.domain.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.Category

class CategoriesViewModel(
    private val repository: CategoriesRepository
) : BaseLoadViewModel<List<Category>>() {

    fun loadCategories() {
        load {
            repository.getCategories()
        }
    }
}