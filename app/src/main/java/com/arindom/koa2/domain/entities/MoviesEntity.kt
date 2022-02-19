package com.arindom.koa2.domain.entities

import com.arindom.koa2.infra.repos.responses.MovieList

data class MoviesEntity(
    val movieList: List<MovieEntity>
) {
    data class MovieEntity(
        val movieId: String,
        val movieName: String,
        val moviePoster: String,
        val year: String,
        //todo covert to enums [Movie,Series]
        val type: String
    )
}

fun MovieList.toMoviesEntity() = MoviesEntity(
    movieList = this.movieList?.map { movie ->
        MoviesEntity.MovieEntity(
            movieId = movie.movieId,
            movieName = movie.title,
            moviePoster = movie.poster,
            year = movie.year,
            type = movie.type
        )
    }?.toList() ?: emptyList()
)
