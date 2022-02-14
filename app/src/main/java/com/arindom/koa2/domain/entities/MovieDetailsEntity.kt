package com.arindom.koa2.domain.entities

data class MovieDetailsEntity(
    val movieId:String,
    val movieName:String,
    val releaseYear: String,
    val genre:String,
    val director:String,
    val actors:List<String>,
    val plot:String,
    val poster:String,
    val rate:String,
    val releaseDate:String,
    val language:String,
    val runTime:String,
    val awards:List<String>
)
