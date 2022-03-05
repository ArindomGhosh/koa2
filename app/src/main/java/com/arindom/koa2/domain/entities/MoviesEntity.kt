package com.arindom.koa2.domain.entities

import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.movie.data.Favourite

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

/*fun MovieList.toMoviesEntity() = MoviesEntity(
    movieList = this.movieList?.map { movie ->
        MoviesEntity.MovieEntity(
            movieId = movie.movieId,
            movieName = movie.title,
            moviePoster = movie.poster,
            year = movie.year,
            type = movie.type
        )
    }?.toList() ?: emptyList()
)*/
fun MovieList.toMoviesEntity() = MoviesEntity(
    movieList = this.movieList.map { movie ->
        MoviesEntity.MovieEntity(
            movieId = movie.movieId,
            movieName = movie.title,
            moviePoster = movie.poster,
            year = movie.year,
            type = movie.type
        )
    }.toList()
)

fun MoviesEntity.MovieEntity.toMovie() = MovieList.Movie(
    movieId = this.movieId,
    title = this.movieName,
    type = this.type,
    poster = this.moviePoster,
    year = this.year
)

fun List<Favourite>.toMoviesEntity() = MoviesEntity(
    movieList = this.map { movie ->
        MoviesEntity.MovieEntity(
            movieId = movie.Uid,
            movieName = movie.name,
            moviePoster = movie.poster,
            year = movie.releaseYear,
            type = movie.type
        )
    }.toList()
)
