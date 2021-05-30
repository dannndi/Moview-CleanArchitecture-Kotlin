package com.dannndi.moview.core.domain.repository

import com.dannndi.moview.core.domain.model.Cast
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieServicesRepository {

    suspend fun getNowPlayingMovie(): Flow<List<Movie>>
    suspend fun getPopularMovie(): Flow<List<Movie>>
    suspend fun getMovieDetail(id: Int): Flow<MovieDetail>
    suspend fun getMovieCast(id: Int): Flow<List<Cast>>

}