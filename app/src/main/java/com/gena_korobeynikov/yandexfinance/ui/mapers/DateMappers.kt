package com.gena_korobeynikov.yandexfinance.ui.mapers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toDateTime() : String {
    val zoneId = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm").withZone(zoneId)
    return formatter.format(this)
}