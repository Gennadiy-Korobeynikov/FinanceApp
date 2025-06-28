package com.gena_korobeynikov.yandexfinance.domain.models

import java.math.BigDecimal
import java.time.Instant

data class Transaction(
    val id: Long,
    val account: Account,
    val category: Category,
    val amount: BigDecimal,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant
)
