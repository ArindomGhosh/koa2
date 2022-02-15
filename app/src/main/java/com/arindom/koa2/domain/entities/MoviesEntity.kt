package com.arindom.koa2.domain.entities

data class MoviesEntity(
    val movieList: List<MovieEntity>
){
    data class MovieEntity(
        val movieId:String,
        val movieName:String,
        val moviePoster:String,
        val year:String,
        //todo covert to enums [Movie,Series]
        val type:String
    )
}
