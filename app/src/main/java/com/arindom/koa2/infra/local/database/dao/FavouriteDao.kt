package com.arindom.koa2.infra.local.database.dao

import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.movie.data.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteDao {
    suspend fun insertFavourite(vararg movie: MovieList.Movie)
    fun getAllFavourites(): Flow<List<Favourite>>
    suspend fun getFavouriteById(uid: String): Favourite?
    suspend fun deleteAllFavourite()
    suspend fun deleteFavouriteById(uid: String)
}