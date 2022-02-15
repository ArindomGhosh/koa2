package com.arindom.koa2.domain.usecases

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.mapToDomainWrapper
import com.arindom.koa2.domain.mappers.IDomainMapper
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.infra.repos.responses.MovieDetail
import com.arindom.koa2.infra.repos.responses.MovieList
import kotlinx.coroutines.flow.single

class MovieDetailUseCase(
    private val searchMovie: ISearchMovie,
    private val moviesDomainWrapper: IDomainMapper<MovieDetail, MovieDetailsEntity>
) {
    suspend fun getMovieDetailsForMovieId(movieId: String): DomainWrapper<MovieDetailsEntity> {
        return searchMovie.getMovieDetails(movieId)
            .single()
            .mapToDomainWrapper(moviesDomainWrapper)
    }
}