package com.arindom.koa2.domain.usecases

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.domain.entities.toMovieDetailsEntity
import com.arindom.koa2.domain.mapToDomainWrapper
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.domain.repos.ISearchMovieKtor
import com.arindom.koa2.infra.repos.responses.serialization.MovieDetail
import kotlinx.coroutines.flow.single

class MovieDetailUseCase(
    private val searchMovie: ISearchMovieKtor,
) {
    suspend fun getMovieDetailsForMovieId(movieId: String): DomainWrapper<MovieDetailsEntity> {
        return searchMovie.getMovieDetails(movieId)
            .single()
            .mapToDomainWrapper(MovieDetail::toMovieDetailsEntity)
    }
}