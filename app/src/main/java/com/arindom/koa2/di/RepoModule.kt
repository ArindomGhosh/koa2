package com.arindom.koa2.di

import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.infra.repos.SearchMoviesImpl
import com.arindom.koa2.infra.services.IServiceCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideSearchMovieRepo(
        serviceCreator: IServiceCreator,
       @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): ISearchMovie {
        return SearchMoviesImpl(
            serviceCreator,
            coroutineDispatcher
        )
    }
}