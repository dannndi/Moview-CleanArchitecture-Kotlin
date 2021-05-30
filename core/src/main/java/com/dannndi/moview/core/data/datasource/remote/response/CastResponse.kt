package com.dannndi.moview.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem>
){
    data class CastItem(
        @field:SerializedName("character")
        val character: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("profile_path")
        val profilePath: String?
    )
}