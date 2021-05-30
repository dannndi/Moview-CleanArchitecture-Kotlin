package com.dannndi.moview.favorite_movie.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.usecase.MovieDaoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieDaoUseCase: MovieDaoUseCase) : ViewModel() {
    private val _listFavoriteMovie = MutableLiveData<List<Movie>>()
    val listFavoriteModule get() = _listFavoriteMovie

    fun getFavoriteMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDaoUseCase.getFavoriteMovies()
                .collect { result ->
                    _listFavoriteMovie.postValue(result)
                }
        }
    }
}