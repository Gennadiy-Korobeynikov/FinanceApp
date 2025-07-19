package com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.use_cases.categories.GetCategoriesUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.GetTransactionByIdUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.UpdateTransactionUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class EditTransactionViewModel @AssistedInject constructor(
    @Assisted private val transactionId: Long,
    private val updateTransactionUC: UpdateTransactionUseCase,
    private val getTransactionUC: GetTransactionByIdUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    var transaction by mutableStateOf<TransactionReadDto?>(null)
        private set

    init {
        viewModelScope.launch {
            transaction = getTransactionUC(transactionId)
        }
    }

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

    fun updateTransaction(
        categoryId: Long,
        amount: String,
        date: LocalDate,
        time: LocalTime,
        comment: String?
    ) {
        val localDateTime = date.atTime(time)
        val instant = localDateTime.toInstant(ZoneOffset.UTC)
        val formatted = DateTimeFormatter.ISO_INSTANT.format(instant)

        val editedTransaction = TransactionWriteDto(
            accountId = ACCOUNT_ID,
            categoryId = categoryId,
            amount = amount.replace(',', '.'),
            transactionDate = formatted,
            comment = comment
        )
        viewModelScope.launch {
            updateTransactionUC(transaction!!.id, editedTransaction)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(accountId: Long): EditTransactionViewModel
    }
}