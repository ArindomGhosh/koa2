package com.arindom.koa2.infra.services.retrofit

import com.arindom.koa2.infra.repos.responses.gson.MovieDetail
import com.arindom.koa2.infra.repos.responses.gson.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbServices {
    @GET("/")
    suspend fun searchMovies(
        @Query("s")searchName:String,
    ):Response<MovieList>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") movieId:String
    ):Response<MovieDetail>
}