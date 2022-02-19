package com.arindom.koa2.domain.usecases

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.entities.toMoviesEntity
import com.arindom.koa2.domain.mapToDomainWrapper
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.infra.repos.responses.MovieList
import kotlinx.coroutines.flow.single

class SearchMoviesUseCase(
    private val searchMovie: ISearchMovie,
) {
    suspend fun getMoviesForName(name: String): DomainWrapper<MoviesEntity> {
        return searchMovie.getMovies(name)
            .single()
            .mapToDomainWrapper(MovieList::toMoviesEntity)
    }
}