package com.gena_korobeynikov.yandexfinance.data.dto

data class TransactionReadDto(
    val id: Long,
    val account : AccountDto,
    val category : CategoryDto,
    val amount : String,
    val transactionDate : String,
    val comment : String? = null,
    val createdAt : String,
    val updatedAt : String
)
