package com.arindom.koa2.di

import com.arindom.koa2.domain.repos.IFavouriteMovies
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.domain.repos.ISearchMovieKtor
import com.arindom.koa2.infra.local.database.dao.FavouriteDao
import com.arindom.koa2.infra.repos.FavouriteMoviesImpl
import com.arindom.koa2.infra.repos.SearchMoviesKtorImpl
import com.arindom.koa2.infra.repos.SearchMoviesRetroImpl
import com.arindom.koa2.infra.services.ktor.OmdbServices
import com.arindom.koa2.infra.services.retrofit.IServiceCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
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
        return SearchMoviesRetroImpl(
            serviceCreator,
            coroutineDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideSearchMovieKtorRepo(
        favouriteDao: FavouriteDao,
        @ApplicationScope coroutineScope: CoroutineScope,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): ISearchMovieKtor {
        return SearchMoviesKtorImpl(
            OmdbServices(),
            favouriteDao,
            coroutineScope,
            coroutineDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideFavouriteMoviesRepo(
        favouriteDao: FavouriteDao,
        @ApplicationScope coroutineScope: CoroutineScope,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): IFavouriteMovies {
        return FavouriteMoviesImpl(
            favouriteDao,
            coroutineScope,
            coroutineDispatcher
        )
    }
}