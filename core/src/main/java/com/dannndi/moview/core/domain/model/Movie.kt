package com.dannndi.moview.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val releaseDate: String,
    val rating: Double,
) : Parcelable
