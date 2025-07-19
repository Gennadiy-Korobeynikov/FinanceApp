package com.gena_korobeynikov.yandexfinance.data.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.syncDataStore by preferencesDataStore(name = "sync_prefs")

class SyncPreferences @Inject constructor(context: Context) {

    private val dataStore = context.syncDataStore

    val lastSyncTimestamp: Flow<Long> = dataStore.data
        .map { it[PreferencesKeys.LAST_SYNC] ?: 0L }

    suspend fun getLastSyncTimestamp(): Long = lastSyncTimestamp.first()

    suspend fun setLastSyncTimestamp(timestamp: Long) {
        dataStore.edit { it[PreferencesKeys.LAST_SYNC] = timestamp }
    }

    private object PreferencesKeys {
        val LAST_SYNC = longPreferencesKey("last_sync_timestamp")
    }
}