package com.gena_korobeynikov.yandexfinance.di.components

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.MainActivity
import com.gena_korobeynikov.yandexfinance.di.modules.CoroutineDispatcherModule
import com.gena_korobeynikov.yandexfinance.di.modules.NetworkModule
import com.gena_korobeynikov.yandexfinance.di.modules.RepositoryModule
import com.gena_korobeynikov.yandexfinance.di.modules.CommonViewModelModule
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
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun accountsComponent(): AccountComponent.Factory
    fun transactionsComponent(): TransactionsComponent.Factory
    fun categoriesComponent(): CategoriesComponent.Factory


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


    //fun viewModelFactory() : ViewModelProvider.Factory
}