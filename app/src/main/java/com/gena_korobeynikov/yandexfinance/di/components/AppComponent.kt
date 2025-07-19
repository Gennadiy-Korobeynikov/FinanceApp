package com.gena_korobeynikov.yandexfinance.di.components

import android.app.Application
import com.gena_korobeynikov.yandexfinance.MainActivity
import com.gena_korobeynikov.yandexfinance.data.network.SyncWorkerFactory
import com.gena_korobeynikov.yandexfinance.di.modules.CommonViewModelModule
import com.gena_korobeynikov.yandexfinance.di.modules.CoroutineDispatcherModule
import com.gena_korobeynikov.yandexfinance.di.modules.DatabaseModule
import com.gena_korobeynikov.yandexfinance.di.modules.NetworkModule
import com.gena_korobeynikov.yandexfinance.di.modules.RepositoryModule
import com.gena_korobeynikov.yandexfinance.di.modules.SyncModule
import com.gena_korobeynikov.yandexfinance.domain.SyncManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        CommonViewModelModule::class,
        NetworkModule::class,
        CoroutineDispatcherModule::class,
        DatabaseModule::class,
        SyncModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun accountsComponent(): AccountComponent.Factory
    fun transactionsComponent(): TransactionsComponent.Factory
    fun categoriesComponent(): CategoriesComponent.Factory

    fun getSyncWorkerFactory(): SyncWorkerFactory

    fun getSyncManager() : SyncManager


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}