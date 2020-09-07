package com.dp.challenge.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(
    crossinline factory: () -> T
) = T::class.java.let {
    ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}

inline fun <reified T : ViewModel> viewModel(
    activity: AppCompatActivity,
    crossinline initializer: () -> T
) =
    lazy(LazyThreadSafetyMode.NONE) { activity.getViewModel { initializer() } }
