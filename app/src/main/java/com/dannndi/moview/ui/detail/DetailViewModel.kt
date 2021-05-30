package com.dannndi.moview.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dannndi.moview.core.domain.model.Cast
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.model.MovieDetail
import com.dannndi.moview.core.domain.usecase.MovieDaoUseCase
import com.dannndi.moview.core.domain.usecase.MovieServicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel constructor(
    private val movieServicesUseCase: MovieServicesUseCase,
    private val movieDaoUseCase: MovieDaoUseCase
) : ViewModel() {
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail get() = _movieDetail

    private val _listCast = MutableLiveData<List<Cast>>()
    val listCast get() = _listCast

    private val _isFavorite = MutableLiveData(false)
    val isFavorite get() = _isFavorite

    fun getMovieDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieServicesUseCase.getMovieDetail(id)
                .collect { result ->
                    _movieDetail.postValue(result)
                }
        }
    }

    fun getMovieCast(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieServicesUseCase.getMovieCast(id)
                .collect { result ->
                    _listCast.postValue(result)
                }
        }
    }

    fun checkIsFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Int = movieDaoUseCase.checkFavoriteMovie(id)
            _isFavorite.postValue(result > 0)
        }
    }

    fun addToFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDaoUseCase.addFavorite(movie)
        }
    }

    fun deleteFromFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDaoUseCase.deleteFavorite(movie)
        }
    }
}