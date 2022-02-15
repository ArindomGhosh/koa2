package com.arindom.koa2.di

import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.mappers.IDomainMapper
import com.arindom.koa2.domain.mappers.MoviesDetailEntityMapper
import com.arindom.koa2.domain.mappers.MoviesEntityDomainMapper
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.domain.usecases.MovieDetailUseCase
import com.arindom.koa2.domain.usecases.SearchMoviesUseCase
import com.arindom.koa2.infra.repos.SearchMoviesImpl
import com.arindom.koa2.infra.repos.responses.MovieDetail
import com.arindom.koa2.infra.repos.responses.MovieList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Singleton
    @Provides
    fun provideMovieDetailUseCase(
        searchMovies: ISearchMovie,
        detailEntityMapper: IDomainMapper<MovieDetail, MovieDetailsEntity>
    ): MovieDetailUseCase {
        return MovieDetailUseCase(
            searchMovies,
            detailEntityMapper
        )
    }

    @Singleton
    @Provides
    fun provideSearchMoviesUseCase(
        searchMovies: ISearchMovie,
        moviesEntityDomainMapper: IDomainMapper<MovieList, MoviesEntity>
    ): SearchMoviesUseCase {
        return SearchMoviesUseCase(
            searchMovies,
            moviesEntityDomainMapper
        )
    }
}