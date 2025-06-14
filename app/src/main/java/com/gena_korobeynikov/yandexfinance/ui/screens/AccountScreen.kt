package com.gena_korobeynikov.yandexfinance.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.models.account
import com.gena_korobeynikov.yandexfinance.models.incomes
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem



    @Composable
    fun AccountScreen() {
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
                        Text(
                            text = "❯",
                            color = colorResource(id = R.color.labels_ternary),
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp,
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

