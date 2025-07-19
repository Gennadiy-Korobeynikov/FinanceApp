package com.gena_korobeynikov.yandexfinance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionEntity
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionWithRelations

@Dao
interface TransactionDao {
    @Transaction
    @Query("Select * from transactions where accountId == :accountId and transactionDate between :start and :end")
    suspend fun getTransactionsForPeriod(accountId : Long ,start: Long, end: Long): List<TransactionWithRelations>

    @Transaction
    @Query("Select * from transactions where id = :id")
    suspend fun getTransactionById(id: String): TransactionWithRelations?

    @Transaction
    @Query("Select * from transactions where updatedAt > :timestamp")
    suspend fun getChangedTransactionsSince(timestamp: Long): List<TransactionWithRelations>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceAllTransactions(transactions: List<TransactionEntity>)
}