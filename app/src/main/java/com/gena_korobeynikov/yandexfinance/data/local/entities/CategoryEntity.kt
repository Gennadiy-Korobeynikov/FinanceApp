package com.gena_korobeynikov.yandexfinance.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)
