package com.gena_korobeynikov.yandexfinance.data

fun String.toSymbol(): String {
    return when (this) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        "GBP" -> "£"
        else -> this
    }
}
