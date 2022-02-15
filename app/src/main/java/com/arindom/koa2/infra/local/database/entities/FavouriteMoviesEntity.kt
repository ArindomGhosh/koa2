package com.arindom.koa2.infra.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMoviesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "movie_name") val movieName: String,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "movie_poster") val poster: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "year") val year: String,
)
