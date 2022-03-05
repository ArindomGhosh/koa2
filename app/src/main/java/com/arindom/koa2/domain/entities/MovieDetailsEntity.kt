package com.arindom.koa2.domain.entities

//import com.arindom.koa2.infra.repos.responses.gson.MovieDetail
import com.arindom.koa2.infra.repos.responses.serialization.MovieDetail

data class MovieDetailsEntity(
    val movieId: String,
    val movieName: String,
    val releaseYear: String,
    val genre: String,
    val director: String,
    val actors: List<String>,
    val plot: String,
    val poster: String,
    val rate: String,
    val releaseDate: String,
    val language: String,
    val runTime: String,
    val awards: List<String>
)

/*fun MovieDetail.toMovieDetailsEntity() = MovieDetailsEntity(
    movieId = this.movieId!!,
    movieName = this.title!!,
    releaseYear = this.year!!,
    genre = this.genre!!,
    director = this.director!!,
    actors = this.actors?.split(", ") ?: emptyList(),
    plot = this.plot!!,
    poster = this.poster!!,
    language = this.language!!,
    awards = this.awards?.split(". ") ?: emptyList(),
    rate = this.rated!!,
    releaseDate = this.released!!,
    runTime = this.runtime!!
)*/

fun MovieDetail.toMovieDetailsEntity() = MovieDetailsEntity(
    movieId = this.movieId,
    movieName = this.title,
    releaseYear = this.year,
    genre = this.genre,
    director = this.director,
    actors = this.actors.split(", ") ?: emptyList(),
    plot = this.plot,
    poster = this.poster,
    language = this.language,
    awards = this.awards.split(". ") ?: emptyList(),
    rate = this.rated,
    releaseDate = this.released,
    runTime = this.runtime
)
