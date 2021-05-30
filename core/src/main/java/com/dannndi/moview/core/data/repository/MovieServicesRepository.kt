package com.dannndi.moview.core.data.repository

import com.dannndi.moview.core.data.datasource.remote.MovieServices
import com.dannndi.moview.core.domain.model.Cast
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.model.MovieDetail
import com.dannndi.moview.core.domain.repository.IMovieServicesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieServicesRepository(
    private val movieServices: MovieServices
) : IMovieServicesRepository {

    override suspend fun getNowPlayingMovie(): Flow<List<Movie>> {
        val result = movieServices.getNowPlayingMovie()
        return flow {
            emit(
                result.results.map {
                    Movie(
                        it.id,
                        it.originalTitle,
                        it.backdropPath,
                        it.releaseDate,
                        it.voteAverage,
                    )
                }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPopularMovie(): Flow<List<Movie>> {
        val result = movieServices.getPopularMovie()
        return flow {
            emit(result.results.map {
                Movie(
                    it.id,
                    it.originalTitle,
                    it.backdropPath,
                    it.releaseDate,
                    it.voteAverage,
                )
            }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetail> {
        val result = movieServices.getMovieDetail(id)
        return flow {
            emit(
                MovieDetail(
                    id = result.id,
                    originalTitle = result.originalTitle,
                    backdropPath = result.backdropPath,
                    posterPath = result.posterPath,
                    releaseDate = result.releaseDate,
                    overview = result.overview,
                    runtime = result.runtime,
                    voteAverage = result.voteAverage,
                    genres = result.genres.map { MovieDetail.Genres(it.name, it.id) },
                    spokenLanguages = result.spokenLanguages.map { MovieDetail.SpokenLanguages(it.name) }
                )
            )
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getMovieCast(id: Int): Flow<List<Cast>> {
        val result = movieServices.getMovieCredits(id)
        return flow {
            emit(
                result.cast.map {
                    Cast(it.name, it.character, it.profilePath)
                }
            )
        }.flowOn(Dispatchers.IO)
    }

}