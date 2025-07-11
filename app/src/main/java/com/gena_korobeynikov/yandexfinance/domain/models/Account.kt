package com.gena_korobeynikov.yandexfinance.domain.models

import java.math.BigDecimal


data class Account(
    val id: Long,
    val name: String,
    val balance: BigDecimal,
    val currency: String
)

