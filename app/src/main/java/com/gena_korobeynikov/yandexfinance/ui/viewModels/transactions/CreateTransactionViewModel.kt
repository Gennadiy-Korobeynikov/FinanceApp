package com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.use_cases.categories.GetCategoriesUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.CreateTransactionUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@TransactionsScope
class CreateTransactionViewModel @Inject  constructor(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    val categories = mutableStateListOf<Category>()
    val isCategoriesLoaded = mutableStateOf(false)

    fun loadCategories(isIncome : Boolean) {
        if (isCategoriesLoaded.value) return
        viewModelScope.launch {
            categories.clear()
            categories.addAll(getCategoriesUseCase(isIncome = isIncome ))
            isCategoriesLoaded.value = true
        }
    }

    fun createTransaction(
        categoryId: Int,
        amount: String,
        date: LocalDate,
        time: LocalTime,
        comment: String
    ) {

       val localDateTime = date.atTime(time)
       val instant = localDateTime.toInstant(ZoneOffset.UTC)
       val formatted = DateTimeFormatter.ISO_INSTANT.format(instant)

       val transaction = TransactionWriteDto(
            accountId = ACCOUNT_ID,
            categoryId = categoryId,
            amount = amount.replace(',', '.'),
            transactionDate = formatted,
            comment = comment
       )
        viewModelScope.launch {
            createTransactionUseCase(transaction)
        }
    }
}