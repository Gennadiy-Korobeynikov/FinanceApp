package com.gena_korobeynikov.yandexfinance.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.common.NetworkModule
import com.gena_korobeynikov.yandexfinance.data.toSymbol
import com.gena_korobeynikov.yandexfinance.domain.Transaction
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import com.gena_korobeynikov.yandexfinance.ui.viewModels.TransactionsViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun HistoryScreen(isIncomes : Boolean = false) {
    val viewModel = remember {
        TransactionsViewModel(
            repository = TransactionsRepositoryImpl(api = NetworkModule.transactionsApi)
        )
    }
    val uiState by viewModel.uiState.collectAsState()

    // По умолчанию: начало месяца и сегодня
    var startDate by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }
    var endDate by remember { mutableStateOf(LocalDate.now()) }
    val accountId: Long = 1

    val context = LocalContext.current
    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }


    if (showStartPicker) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                startDate = LocalDate.of(year, month + 1, dayOfMonth)
                showStartPicker = false
            },
            startDate.year, startDate.monthValue - 1, startDate.dayOfMonth
        )
        datePickerDialog.setOnDismissListener {
            showStartPicker = false
        }
        datePickerDialog.show()
    }

    if (showEndPicker) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                endDate = LocalDate.of(year, month + 1, dayOfMonth)
                showEndPicker = false
            },
            endDate.year, endDate.monthValue - 1, endDate.dayOfMonth
        )
        datePickerDialog.setOnDismissListener {
            showEndPicker = false
        }
        datePickerDialog.show()
    }


    LaunchedEffect(startDate, endDate) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        viewModel.loadTransactions(
            accountId,
            startDate.format(formatter),
            endDate.format(formatter)
        )
    }

    Column {
        MainListItem(
            mainText = stringResource(id = R.string.start),
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = startDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_vert),
                    contentDescription = null,
                )
            },
            modifier = Modifier.clickable { showStartPicker = true }
        )
        MainListItem(
            mainText = stringResource(id = R.string.end),
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = endDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_vert),
                    contentDescription = null,
                )
            },
            modifier = Modifier.clickable { showEndPicker = true }
        )




        when (uiState) {
            is TransactionUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is TransactionUiState.Success -> {
                val expenses =
                    (uiState as TransactionUiState.Success).transactions.filter { it.category.isIncome == isIncomes }
                        .sortedByDescending { it.transactionDate }


                MainListItem(
                    mainText = stringResource(id = R.string.sum),
                    color = colorResource(id = R.color.secondary_green),
                    huggingHeight = true,
                    trailing = {
                        Text(
                            text = "${expenses.sumOf { it.amount }} ${expenses.firstOrNull()?.account?.currency?.toSymbol()?: ""}",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                )
                HistoryList(expenses)
            }

            is TransactionUiState.Error -> {
                val message = (uiState as TransactionUiState.Error).message
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ошибка: $message", color = Color.Red)
                }
            }

        }
    }
}





@Composable
fun HistoryList(transactions: List<Transaction>) {
    val zoneId = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm").withZone(zoneId)
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        items(transactions, key = { it.id }) { expense ->
            MainListItem(
                emoji = expense.category.emoji,
                mainText = expense.category.name,
                subtitle = expense.comment,
                trailing = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${expense.amount} ${expense.account.currency.toSymbol()}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                            Text(
                                text = formatter.format(expense.transactionDate),
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                        }

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


