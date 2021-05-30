package com.dannndi.moview.core.data.datasource.remote

import com.dannndi.moview.core.data.datasource.remote.response.CastResponse
import com.dannndi.moview.core.data.datasource.remote.response.MovieDetailResponse
import com.dannndi.moview.core.data.datasource.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val KEY = "9c4321103577c8fe48972c0207dc1cba"
    }

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "en-US",
        @Query("api_key") page: String = "1",
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "en-US",
        @Query("api_key") page: String = "1",
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "en-US"
    ): MovieDetailResponse

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "en-US"
    ): CastResponse
}