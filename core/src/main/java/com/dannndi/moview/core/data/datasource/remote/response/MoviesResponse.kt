package com.dannndi.moview.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("results")
    val results: List<MovieItem>
) {

    data class MovieItem(
        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("original_title")
        val originalTitle: String,

        @field:SerializedName("backdrop_path")
        val backdropPath: String,

        @field:SerializedName("release_date")
        val releaseDate: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double,


        )
}
