package com.gena_korobeynikov.yandexfinance.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.models.currencyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheetContent(
    onCurrencySelected: (String) -> Unit,
    onCancel: () -> Unit
) {
    Column {
        LazyColumn {
            items(currencyList, key = { it.id }) { currency ->
                ListItem(
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = currency.icon),
                            contentDescription = currency.name,
                        )
                    },
                    headlineContent = {
                        Text(
                            text = "${currency.name} ${currency.symbol}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(id = R.color.on_surface),
                        )
                    },
                    modifier = androidx.compose.ui.Modifier
                        .clickable { onCurrencySelected(currency.code) }
                )
            }

            item {
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = colorResource(id = R.color.error),
                    ),
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cross_in_circle),
                            contentDescription = stringResource(R.string.cancel),
                        )
                    },
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(id = R.color.white),
                        )
                    },
                    modifier = androidx.compose.ui.Modifier
                        .clickable { onCancel() }
                )
            }
        }
    }
}