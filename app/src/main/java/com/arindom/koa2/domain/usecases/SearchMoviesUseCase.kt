package com.arindom.koa2.domain.usecases

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.mapToDomainWrapper
import com.arindom.koa2.domain.mappers.IDomainMapper
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.infra.repos.responses.MovieList
import kotlinx.coroutines.flow.single

class SearchMoviesUseCase(
    private val searchMovie: ISearchMovie,
    private val moviesDomainWrapper: IDomainMapper<MovieList, MoviesEntity>
) {
    suspend fun getMoviesForName(name: String): DomainWrapper<MoviesEntity> {
        return searchMovie.getMovies(name)
            .single()
            .mapToDomainWrapper(moviesDomainWrapper)
    }
}