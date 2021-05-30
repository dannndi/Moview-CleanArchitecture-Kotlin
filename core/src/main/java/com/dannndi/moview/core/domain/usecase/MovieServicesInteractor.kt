package com.dannndi.moview.core.domain.usecase

import com.dannndi.moview.core.domain.model.Cast
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.core.domain.model.MovieDetail
import com.dannndi.moview.core.domain.repository.IMovieServicesRepository
import kotlinx.coroutines.flow.Flow

class MovieServicesInteractor(
    private val movieServicesRepository: IMovieServicesRepository
) : MovieServicesUseCase {
    override suspend fun getNowPlayingMovie(): Flow<List<Movie>> {
        return movieServicesRepository.getNowPlayingMovie()
    }

    override suspend fun getPopularMovie(): Flow<List<Movie>> {
        return movieServicesRepository.getPopularMovie()
    }

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetail> {
        return movieServicesRepository.getMovieDetail(id)
    }

    override suspend fun getMovieCast(id: Int): Flow<List<Cast>> {
        return movieServicesRepository.getMovieCast(id)
    }

}