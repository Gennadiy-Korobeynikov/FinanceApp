package com.gena_korobeynikov.yandexfinance.domain

import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.toDomain

class CategoriesRepositoryImpl (
    private val api: CategoriesApi
) : CategoriesRepository {

    override suspend fun getCategories(): List<Category> {
        return api.getCategories().map { it.toDomain() }
    }


}