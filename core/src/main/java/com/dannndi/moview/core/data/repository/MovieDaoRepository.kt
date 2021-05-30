package com.dannndi.moview.core.data.repository

import com.dannndi.moview.core.data.datasource.local.MovieDao
import com.dannndi.moview.core.data.datasource.local.entity.MovieEntity
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.repository.IMovieDaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDaoRepository(private val movieDao: MovieDao) : IMovieDaoRepository {

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> =
        movieDao.getFavoriteMovies().map {
            it.map { movieEntity ->
                Movie(
                    movieEntity.id,
                    movieEntity.title,
                    movieEntity.backdropPath,
                    movieEntity.releaseDate,
                    movieEntity.rating,
                )
            }
        }

    override suspend fun checkFavoriteMovie(id: Int): Int =
        movieDao.checkFavoriteMovie(id)

    override suspend fun addFavorite(movie: Movie) {
        val movieEntity = MovieEntity(
            movie.id,
            movie.title,
            movie.backdropPath,
            movie.releaseDate,
            movie.rating,
        )
        movieDao.addFavorite(movieEntity)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        val movieEntity = MovieEntity(
            movie.id,
            movie.title,
            movie.backdropPath,
            movie.releaseDate,
            movie.rating,
        )
        movieDao.deleteFavorite(movieEntity)
    }
}