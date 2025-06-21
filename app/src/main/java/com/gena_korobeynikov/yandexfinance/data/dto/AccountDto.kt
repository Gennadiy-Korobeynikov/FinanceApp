package com.gena_korobeynikov.yandexfinance.data.dto

data class AccountDto(
    val id: Long,
    val name: String,
    val balance: String,
    val currency: String
)

