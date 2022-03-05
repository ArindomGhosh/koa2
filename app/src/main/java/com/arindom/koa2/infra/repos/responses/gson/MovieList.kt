package com.arindom.koa2.infra.repos.responses.gson

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Search")
    val movieList: List<Movie>?,
    @SerializedName("Response")
    val isValidResponse: Boolean,
    @SerializedName("Error")
    val errorMessage: String?
) {
    data class Movie(
        @SerializedName("Title")
        val title: String,
        @SerializedName("Year")
        val year: String,
        @SerializedName("imdbID")
        val movieId: String,
        @SerializedName("Type")
        val type: String,
        @SerializedName("Poster")
        val poster: String,
    )
}
