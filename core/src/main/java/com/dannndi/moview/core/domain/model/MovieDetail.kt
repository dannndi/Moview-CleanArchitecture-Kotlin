package com.dannndi.moview.core.domain.model

class MovieDetail(

    val backdropPath: String,

    val overview: String,

    val spokenLanguages: List<SpokenLanguages>,

    val originalTitle: String,

    val releaseDate: String,

    val genres: List<Genres>,

    val voteAverage: Double,

    val runtime: Int,

    val id: Int,

    val posterPath: String
) {

    data class Genres(

        val name: String,

        val id: Int
    )

    data class SpokenLanguages(

        val name: String
    )
}

