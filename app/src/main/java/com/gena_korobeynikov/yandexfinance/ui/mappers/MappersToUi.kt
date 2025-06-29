package com.gena_korobeynikov.yandexfinance.ui.mappers

import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi
import com.gena_korobeynikov.yandexfinance.ui.models.CategoryUi
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionUi

fun Transaction.toUi(): TransactionUi {
    return TransactionUi(
        id = id,
        currency = account.currency.toSymbol(),
        amount = amount.toMoneyFormat(),
        transactionDate = transactionDate.toDateTime(),
        comment = comment,
        emoji = category.emoji,
        categoryName = category.name
    )
}

fun Account.toUi(): AccountUi {
    return AccountUi(
        id = id,
        name = name,
        balance = balance.toMoneyFormat(),
        currency = currency.toSymbol()
    )
}

fun Category.toUi(): CategoryUi {
    return CategoryUi(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}