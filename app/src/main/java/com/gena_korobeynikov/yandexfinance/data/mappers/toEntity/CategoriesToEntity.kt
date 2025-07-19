package com.gena_korobeynikov.yandexfinance.data.mappers.toEntity

import com.gena_korobeynikov.yandexfinance.data.local.entities.CategoryEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Category

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        isIncome = isIncome,
        emoji = emoji,
    )
}