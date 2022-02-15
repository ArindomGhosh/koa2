package com.arindom.koa2.infra.local.database.entities

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Query("Select * from favourite_movies")
    suspend fun getMyFavorites(): List<FavouriteMoviesEntity>
}