package com.gena_korobeynikov.yandexfinance.data.mappers.toDto

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.AccountEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Account


fun AccountEntity.toDto(): AccountDto {

    return AccountDto(
        id = id,
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}

fun Account.toDto(): AccountDto {
    return AccountDto(
        id = id,
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}