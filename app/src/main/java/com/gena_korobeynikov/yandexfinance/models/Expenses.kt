package com.gena_korobeynikov.yandexfinance.models

data class Expenses (

    val id: Int,
    val value: String,
    val title: String,
    val subtitle: String? = null,
    val emoji: String? = null,
)