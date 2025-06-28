package com.gena_korobeynikov.yandexfinance.ui.mapers
import java.text.DecimalFormatSymbols
import java.math.BigDecimal
import java.util.Locale
import java.text.DecimalFormat

fun String.toSymbol(): String {
    return when (this) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        "GBP" -> "£"
        else -> this
    }
}

fun BigDecimal.toMoneyFormat(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = ' '  // Пробел как разделитель тысяч
        decimalSeparator = ','   // Запятая как десятичный разделитель
    }

    val formatter = DecimalFormat("#,##0.00", symbols)
    return formatter.format(this)
}