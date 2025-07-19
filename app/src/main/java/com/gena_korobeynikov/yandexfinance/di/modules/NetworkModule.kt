package com.gena_korobeynikov.yandexfinance.di.modules

import android.app.Application
import com.gena_korobeynikov.yandexfinance.BuildConfig
import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitor
import com.gena_korobeynikov.yandexfinance.data.network.NetworkMonitorImpl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }
    @Provides
    fun providesTransactionsApi(retrofit : Retrofit): TransactionsApi {
       return retrofit.create(TransactionsApi::class.java)
    }

    @Provides
    fun providesAccountApi(retrofit : Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    fun providesCategoryApi(retrofit : Retrofit): CategoriesApi  {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(application: Application) : NetworkMonitor {
        return NetworkMonitorImpl(application.applicationContext)
    }

}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .build()
        return chain.proceed(request)
    }
}