package com.arindom.koa2.domain.repos


import com.arindom.koa2.infra.repos.responses.serialization.MovieDetail
import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import kotlinx.coroutines.flow.Flow

interface ISearchMovieKtor {
    fun getMovies(movieName: String): Flow<ApiResponse<MovieList>>
    fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>>
    fun saveFavoriteMovie(movie: MovieList.Movie)
    fun deleteFavoriteMovie(movieId: String)
}
