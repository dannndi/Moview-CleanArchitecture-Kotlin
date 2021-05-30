package com.dannndi.moview.favorite_movie.di

import com.dannndi.moview.favorite_movie.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}