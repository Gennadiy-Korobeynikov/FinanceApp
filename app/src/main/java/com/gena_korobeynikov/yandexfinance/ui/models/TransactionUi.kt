package com.gena_korobeynikov.yandexfinance.ui.models

import java.math.BigDecimal
import java.time.Instant


data class TransactionListUi(
    val list: List<TransactionUi>,
    val totalSum: String
)


data class TransactionUi(
    val id: Long,
    val currency : String,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val emoji : String,
    val categoryName : String
)
