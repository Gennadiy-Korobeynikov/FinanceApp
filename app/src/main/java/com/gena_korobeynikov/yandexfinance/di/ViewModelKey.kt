package com.gena_korobeynikov.yandexfinance.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME) // В какой момент аннотация будет доступна
@Target(AnnotationTarget.FUNCTION) // Аннотация будет применяться к функциям
annotation class ViewModelKey(val value: KClass<out ViewModel>)
