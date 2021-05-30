package com.dannndi.moview.di

import com.dannndi.moview.ui.detail.DetailViewModel
import com.dannndi.moview.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}