package com.gena_korobeynikov.yandexfinance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gena_korobeynikov.yandexfinance.data.local.dao.AccountDao
import com.gena_korobeynikov.yandexfinance.data.local.dao.CategoryDao
import com.gena_korobeynikov.yandexfinance.data.local.dao.TransactionDao
import com.gena_korobeynikov.yandexfinance.data.local.entities.AccountEntity
import com.gena_korobeynikov.yandexfinance.data.local.entities.CategoryEntity
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}