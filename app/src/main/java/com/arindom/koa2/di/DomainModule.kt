package com.arindom.koa2.di

import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.mappers.IDomainMapper
import com.arindom.koa2.domain.mappers.MoviesDetailEntityMapper
import com.arindom.koa2.domain.mappers.MoviesEntityDomainMapper
import com.arindom.koa2.infra.repos.responses.MovieDetail
import com.arindom.koa2.infra.repos.responses.MovieList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideMoviesDetailDomainMapper(): IDomainMapper<MovieDetail, MovieDetailsEntity> {
        return MoviesDetailEntityMapper()
    }

    @Singleton
    @Provides
    fun provideMoviesEntityDomainMapper(): IDomainMapper<MovieList, MoviesEntity> {
        return MoviesEntityDomainMapper()
    }
}