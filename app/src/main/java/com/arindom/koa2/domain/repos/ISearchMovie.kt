package com.arindom.koa2.domain.repos

import com.arindom.koa2.infra.repos.responses.gson.MovieDetail
import com.arindom.koa2.infra.repos.responses.gson.MovieList
import kotlinx.coroutines.flow.Flow

interface ISearchMovie {
    fun getMovies(movieName: String): Flow<ApiResponse<MovieList>>
    fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>>
}
