package com.gena_korobeynikov.yandexfinance.ui.models

import androidx.annotation.DrawableRes
import com.gena_korobeynikov.yandexfinance.R

data class CurrencyUi(
    val id : Int,
    val name: String,
    val symbol : String,
    val code: String,
    @DrawableRes val icon : Int
)

object Currencies {
    val ruble = CurrencyUi(1, "Российский рубль", "₽", "RUB", R.drawable.ic_currency_ruble)
    val dollar = CurrencyUi(2, "Американский доллар", "$","USD" ,R.drawable.ic_currency_dollar)
    val euro = CurrencyUi(3, "Евро", "€", "EUR", R.drawable.ic_currency_euro)
}

val currencyList= listOf(
    Currencies.ruble,
    Currencies.dollar,
    Currencies.euro
)