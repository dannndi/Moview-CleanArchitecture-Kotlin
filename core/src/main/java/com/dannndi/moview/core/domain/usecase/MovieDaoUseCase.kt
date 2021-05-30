package com.dannndi.moview.core.domain.usecase

import com.dannndi.moview.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDaoUseCase {
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun checkFavoriteMovie(id: Int): Int
    suspend fun addFavorite(movie: Movie)
    suspend fun deleteFavorite(movie: Movie)
}