package com.arindom.koa2.domain.repos.responses

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("Response")
    val isValidResponse: Boolean,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("Rated")
    val rated: String?,
    @SerializedName("Released")
    val released: String?,
    @SerializedName("Runtime")
    val runtime: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("Language")
    val language: String?,
    @SerializedName("Awards")
    val awards: String?,
    @SerializedName("Poster")
    val poster: String?,
    @SerializedName("imdbID")
    val movieId: String?,
    @SerializedName("Error")
    val errorMessage:String?
)
