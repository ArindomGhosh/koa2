package com.arindom.koa2.infra.repos.responses.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieList(
    @SerialName("Search")
    val movieList: List<Movie> = emptyList(),
    @SerialName("Response")
    val isValidResponse: Boolean = false,
    @SerialName("Error")
    val errorMessage: String? = null
) {
    @Serializable
    data class Movie(
        @SerialName("Title")
        val title: String,
        @SerialName("Year")
        val year: String,
        @SerialName("imdbID")
        val movieId: String,
        @SerialName("Type")
        val type: String,
        @SerialName("Poster")
        val poster: String,
    )
}
