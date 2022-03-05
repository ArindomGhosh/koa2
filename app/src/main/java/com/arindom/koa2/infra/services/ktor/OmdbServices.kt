package com.arindom.koa2.infra.services.ktor

import io.ktor.client.request.*
import io.ktor.client.statement.*

class OmdbServices {
    suspend fun searchMovies(name: String) = client_kotlin_serializer.get<HttpResponse>("/") {
        parameter("s", name)
    }

    suspend fun getMoviesDetails(movieId: String) = client_kotlin_serializer.get<HttpResponse>("/") {
        parameter("i", movieId)
    }
}