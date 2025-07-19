package com.gena_korobeynikov.yandexfinance.data.mappers.toDomain

import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.CategoryEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Category

fun CategoryDto.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}