package com.gena_korobeynikov.yandexfinance.ui.viewModels.categories

import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.use_cases.categories.GetCategoriesUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.CategoryUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.BaseLoadViewModel
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategories : GetCategoriesUseCase
) : BaseLoadViewModel<List<Category>, List<CategoryUi>>() {

    fun loadCategories(isIncome : Boolean? = null) {
        load(
            mapper = { list -> list.map { it.toUi() } },
            block = { getCategories(isIncome) }
        )
    }
}