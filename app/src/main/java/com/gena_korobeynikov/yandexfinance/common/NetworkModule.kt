package com.gena_korobeynikov.yandexfinance.common

import com.gena_korobeynikov.yandexfinance.BuildConfig
import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.api.CategoriesApi
import com.gena_korobeynikov.yandexfinance.data.api.TransactionsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val transactionsApi: TransactionsApi by lazy {
        retrofit.create(TransactionsApi::class.java)
    }

    val accountApi: AccountApi by lazy {
        retrofit.create(AccountApi::class.java)
    }

    val categoryApi: CategoriesApi by lazy {
        retrofit.create(CategoriesApi::class.java)
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