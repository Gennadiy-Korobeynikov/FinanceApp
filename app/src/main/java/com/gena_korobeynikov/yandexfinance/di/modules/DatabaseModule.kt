package com.gena_korobeynikov.yandexfinance.di.modules

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.gena_korobeynikov.yandexfinance.data.local.AppDatabase
import com.gena_korobeynikov.yandexfinance.data.local.OfflineIdProvider
import com.gena_korobeynikov.yandexfinance.data.local.OfflineIdProviderImpl
import com.gena_korobeynikov.yandexfinance.data.local.dao.AccountDao
import com.gena_korobeynikov.yandexfinance.data.local.dao.CategoryDao
import com.gena_korobeynikov.yandexfinance.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application : Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "yandex_finance_db"
        ).build()
    }

    @Provides
    fun provideTransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()

    @Provides
    fun provideAccountDao(db: AppDatabase): AccountDao = db.accountDao()

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()


    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { application.applicationContext.dataStoreFile("settings.preferences_pb") }
        )
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideOfflineIdProvider(
        dataStore: DataStore<Preferences>
    ): OfflineIdProvider {
        return OfflineIdProviderImpl(dataStore)
    }

}