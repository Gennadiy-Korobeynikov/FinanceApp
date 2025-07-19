package com.gena_korobeynikov.yandexfinance.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OfflineIdProviderImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OfflineIdProvider {

    companion object {
        private val OFFLINE_ID_KEY = longPreferencesKey("offline_id_counter")
        private const val DEFAULT_START = -1L
    }

    override suspend fun getNextId(): Long {
        val prefs = dataStore.data.first()
        val current = prefs[OFFLINE_ID_KEY] ?: DEFAULT_START
        val next = current - 1
        dataStore.edit { it[OFFLINE_ID_KEY] = next }
        return current
    }
}
