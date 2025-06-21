package com.gena_korobeynikov.yandexfinance.domain

data class Category(
    val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)
