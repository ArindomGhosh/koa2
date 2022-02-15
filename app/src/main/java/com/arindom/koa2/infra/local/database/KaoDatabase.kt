package com.arindom.koa2.infra.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arindom.koa2.infra.local.database.entities.FavoriteMovieDao
import com.arindom.koa2.infra.local.database.entities.FavouriteMoviesEntity

@Database(
    entities = [FavouriteMoviesEntity::class],
    version = 1
)
abstract class KaoDatabase : RoomDatabase() {
    abstract fun getFavouriteMovieDao(): FavoriteMovieDao
}