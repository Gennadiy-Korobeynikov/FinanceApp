package com.gena_korobeynikov.yandexfinance.domain.models

data class Category(
    val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)
