package com.arindom.koa2.di

import com.arindom.koa2.domain.repos.IFavouriteMovies
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.domain.repos.ISearchMovieKtor
import com.arindom.koa2.domain.usecases.FavouriteMovieUseCase
import com.arindom.koa2.domain.usecases.MovieDetailUseCase
import com.arindom.koa2.domain.usecases.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
/*    @Singleton
    @Provides
    fun provideMovieDetailUseCase(
        searchMovies: ISearchMovie,
    ): MovieDetailUseCase {
        return MovieDetailUseCase(
            searchMovies
        )
    }

    @Singleton
    @Provides
    fun provideSearchMoviesUseCase(
        searchMovies: ISearchMovie,
    ): SearchMoviesUseCase {
        return SearchMoviesUseCase(
            searchMovies
        )
    }*/

    @Singleton
    @Provides
    fun provideMovieDetailUseCase(
        searchMovies: ISearchMovieKtor,
    ): MovieDetailUseCase {
        return MovieDetailUseCase(
            searchMovies
        )
    }

    @Singleton
    @Provides
    fun provideSearchMoviesUseCase(
        searchMovies: ISearchMovieKtor,
    ): SearchMoviesUseCase {
        return SearchMoviesUseCase(
            searchMovies
        )
    }
    @Singleton
    @Provides
    fun providefavouriteMovieUseCase(
        favouriteMovies: IFavouriteMovies,
    ): FavouriteMovieUseCase {
        return FavouriteMovieUseCase(
            favouriteMovies
        )
    }
}