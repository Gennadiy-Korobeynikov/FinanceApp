package com.gena_korobeynikov.yandexfinance.data.dto

data class TransactionWriteDto(
    val accountId : Long,
    val categoryId : Int,
    val amount : String,
    val transactionDate : String,
    val comment : String,
)
