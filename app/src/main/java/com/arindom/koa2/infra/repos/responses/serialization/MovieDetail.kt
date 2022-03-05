package com.arindom.koa2.infra.repos.responses.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    @SerialName("Response")
    val isValidResponse: Boolean = false,
    @SerialName("Title")
    val title: String = "",
    @SerialName("Year")
    val year: String = "",
    @SerialName("Rated")
    val rated: String = "",
    @SerialName("Released")
    val released: String = "",
    @SerialName("Runtime")
    val runtime: String = "",
    @SerialName("Genre")
    val genre: String = "",
    @SerialName("Director")
    val director: String = "",
    @SerialName("Actors")
    val actors: String = "",
    @SerialName("Plot")
    val plot: String = "",
    @SerialName("Language")
    val language: String = "",
    @SerialName("Awards")
    val awards: String = "",
    @SerialName("Poster")
    val poster: String = "",
    @SerialName("imdbID")
    val movieId: String = "",
    @SerialName("Error")
    val errorMessage: String? = null
)