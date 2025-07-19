package com.gena_korobeynikov.yandexfinance.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithRelations(
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "accountId",
        entityColumn = "id"
    )
    val account: AccountEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)
