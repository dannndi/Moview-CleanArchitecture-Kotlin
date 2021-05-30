package com.dannndi.moview.core.domain.repository

import com.dannndi.moview.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDaoRepository {
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun checkFavoriteMovie(id: Int): Int
    suspend fun addFavorite(movie: Movie)
    suspend fun deleteFavorite(movie: Movie)
}