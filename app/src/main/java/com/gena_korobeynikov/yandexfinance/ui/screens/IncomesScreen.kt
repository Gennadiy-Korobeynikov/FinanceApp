package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
import com.gena_korobeynikov.yandexfinance.models.Incomes
import com.gena_korobeynikov.yandexfinance.models.expenses
import com.gena_korobeynikov.yandexfinance.models.incomes
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem

const val TOTAL_INCOMES = "600 000 ₽" // Пока здесь, потом с бэка

@Composable
    fun IncomesScreen() {


        Column {
            MainListItem(
                mainText = "Всего",
                color = colorResource(id = R.color.secondary_green),
                huggingHeight = true,
                trailing = {
                    Text(
                        text = TOTAL_INCOMES,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            )

            IncomesList(incomes)
        }
    }



@Composable
fun IncomesList(incomes: List<Incomes>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(incomes, key = { it.id }) { income ->
            MainListItem(
                mainText = income.title,
                trailing = {
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(
                            text = "${income.value} ₽",
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
