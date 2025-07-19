package com.gena_korobeynikov.yandexfinance.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: Long,
    val accountId: Long,
    val categoryId: Long,
    val amount: Double,
    val transactionDate: Long,
    val comment: String?,
    val createdAt: Long,
    val updatedAt: Long,
)
