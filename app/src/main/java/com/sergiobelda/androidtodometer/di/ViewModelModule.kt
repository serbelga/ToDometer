package com.sergiobelda.androidtodometer.di

import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get(), get()) }
}