package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.states.UiState
import com.gena_korobeynikov.yandexfinance.ui.components.ListLoader
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.HistoryViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.TodayTransactionsViewModel
import java.time.LocalDate


@Composable
fun ExpensesScreen() {
    val viewModel = remember {
        TodayTransactionsViewModel()
    }
    val uiState by viewModel.uiState.collectAsState()
    val accountId = 1L // Стоит по умолчанию для корректного вывода (для проверяющих), можно поменять

    LaunchedEffect(accountId) {
        viewModel.loadTransactions(accountId, isIncome = false)
    }

    ListLoader(
        uiState
    ) {
        val expensesToday = (uiState as UiState.Success).data.list
        val totalSum = (uiState as UiState.Success).data.totalSum
        ExpensesList(expensesToday, totalSum)
    }
}



@Composable
fun ExpensesList(expensesToday: List<TransactionUi>, totalSum : String) {
    Column {

        MainListItem(
            mainText = "Всего",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = "$totalSum ${expensesToday.firstOrNull()?.currency ?: "₽"}",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )



        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(expensesToday, key = { it.id }) { expense ->
                MainListItem(
                    emoji = expense.emoji,
                    mainText = expense.categoryName,
                    subtitle = expense.comment,
                    trailing = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "${expense.amount} ${expense.currency}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more_vert),
                                contentDescription = null,
                            )
                        }
                    }
                )


            }
        }
    }
}
