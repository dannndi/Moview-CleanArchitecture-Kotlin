package com.dannndi.moview.core.domain.usecase

import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.repository.IMovieDaoRepository
import kotlinx.coroutines.flow.Flow

class MovieDaoInteractor(
    private val movieDaoRepository: IMovieDaoRepository
) : MovieDaoUseCase {
    override suspend fun getFavoriteMovies(): Flow<List<Movie>> =
        movieDaoRepository.getFavoriteMovies()

    override suspend fun checkFavoriteMovie(id: Int): Int =
        movieDaoRepository.checkFavoriteMovie(id)

    override suspend fun addFavorite(movie: Movie) =
        movieDaoRepository.addFavorite(movie)

    override suspend fun deleteFavorite(movie: Movie) =
        movieDaoRepository.deleteFavorite(movie)
}