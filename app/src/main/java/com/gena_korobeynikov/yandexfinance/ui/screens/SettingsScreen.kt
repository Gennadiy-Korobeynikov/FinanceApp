package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem


@Composable
    fun SettingsScreen() {
        val itemTitles = listOf(
            stringResource(id = R.string.main_color),
            stringResource(id = R.string.sounds),
            stringResource(id = R.string.haptics),
            stringResource(id = R.string.code_password),
            stringResource(id = R.string.synchronization),
            stringResource(id = R.string.language),
            stringResource(id = R.string.about)
        )

        SettingsList(
            itemTitles = itemTitles,
        )

    }

    @Composable
    fun SettingsList(
        itemTitles: List<String>,
        modifier: Modifier = Modifier,
    ) {
        Column(modifier = modifier) {

            MainListItem(
                mainText = stringResource(id = R.string.light_theme_auto),
                huggingHeight = true,
                trailing = {
                    Switch(
                        checked = false,
                        onCheckedChange = {},
                        modifier = Modifier.height(32.dp)
                    )
                }
            )

            itemTitles.forEach { mainText ->
                MainListItem(
                    mainText = mainText,
                    huggingHeight = true,
                    trailing = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_horiz),
                            contentDescription = null,
                        )
                    }

                )
            }
        }
    }
