package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.gena_korobeynikov.yandexfinance.ui.mapers.toSymbol
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.ui.states.UiState
import com.gena_korobeynikov.yandexfinance.ui.components.ListLoader
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.AccountViewModel


@Composable
    fun AccountScreen(
        accountId: Long = 1, // Стоит по умолчанию для корректного вывода (для проверяющих), можно поменять
    ) {
    val viewModel = remember {
        AccountViewModel()
    }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(accountId) {
        viewModel.loadAccount(accountId)
    }


    ListLoader(uiState) {
        val account = (uiState as UiState.Success).data
        AccountInfo(account)
    }

}


@Composable
fun AccountInfo(
    account: AccountUi
) {
    Column {
        MainListItem(
            mainText = "Баланс",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            emoji = "💰",
            emojiWhiteBg = true,
            trailing = {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "${account.balance} ${account.currency}",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                }
            }
        )

        MainListItem(
            mainText = "Валюта",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = account.currency,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier.fillMaxWidth().height(233.dp),
            painter = painterResource(id = R.drawable.mock_diagram),
            contentDescription = "Диаграмма",
        )

    }
}

