package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.models.Expenses
import com.gena_korobeynikov.yandexfinance.models.expenses
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem

const val TOTAL_EXPENSES = "436 558 ₽" // Пока здесь, потом с бэка

    @Composable
    fun ExpensesScreen() {
        Column {
            MainListItem(
                mainText = "Всего",
                color = colorResource(id = R.color.secondary_green),
                huggingHeight = true,
                trailing = {
                    Text(
                        text = TOTAL_EXPENSES,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            )

            ExpensesList(expenses)
        }
    }



@Composable
fun ExpensesList(expenses: List<Expenses>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(expenses, key = { it.id }) { expense ->
            MainListItem(
                emoji = expense.emoji,
                mainText = expense.title,
                subtitle = expense.subtitle,
                trailing = {
                    Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(
                            text = "${expense.value} ₽",
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
