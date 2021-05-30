package com.dannndi.moview.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.usecase.MovieServicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val movieServicesUseCase: MovieServicesUseCase
) : ViewModel() {

    init {
        getPopularMovie()
        getNowPlayingMovie()
    }

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovie get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies get() = _nowPlayingMovies

    private fun getPopularMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieServicesUseCase.getPopularMovie()
                .collect { result ->
                    _popularMovies.postValue(result)

                }
        }
    }

    fun getNowPlayingMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieServicesUseCase.getNowPlayingMovie()
                .collect { result ->
                    _nowPlayingMovies.postValue(result)
                }
        }
    }
}