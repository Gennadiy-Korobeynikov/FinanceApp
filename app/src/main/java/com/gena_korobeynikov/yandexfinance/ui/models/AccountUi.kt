package com.gena_korobeynikov.yandexfinance.ui.models

import java.math.BigDecimal


data class AccountUi(
    val id: Long,
    val name: String,
    val balance: String,
    val currency: String
)

