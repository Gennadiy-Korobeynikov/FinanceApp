package com.gena_korobeynikov.yandexfinance.data.mappers.toDto

import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.CategoryEntity


fun CategoryEntity.toDto(): CategoryDto {

    return CategoryDto(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}