package com.arindom.koa2.domain.repos

import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.movie.data.Favourite
import kotlinx.coroutines.flow.Flow

interface IFavouriteMovies {
    fun saveFavoriteMovie(movie: MovieList.Movie)
    fun getAllFavouriteMovies(): Flow<ApiResponse<List<Favourite>>>
    fun deleteFavoriteMovie(movieId: String)
    fun deleteAllFavourites()
}