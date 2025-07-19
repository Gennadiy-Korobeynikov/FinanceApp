package com.gena_korobeynikov.yandexfinance.data.dto

data class TransactionWriteDto(
    val accountId : Long,
    val categoryId : Long,
    val amount : String,
    val transactionDate : String,
    val comment : String?,
)
