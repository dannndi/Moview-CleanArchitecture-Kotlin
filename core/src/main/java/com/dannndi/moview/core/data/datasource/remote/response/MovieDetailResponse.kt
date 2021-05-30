package com.dannndi.moview.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

class MovieDetailResponse(

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String
) {

    data class GenresItem(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int
    )

    data class SpokenLanguagesItem(

        @field:SerializedName("name")
        val name: String
    )
}

