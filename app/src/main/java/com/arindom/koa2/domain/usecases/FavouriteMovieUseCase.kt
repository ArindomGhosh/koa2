package com.arindom.koa2.domain.usecases

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.entities.toMovie
import com.arindom.koa2.domain.entities.toMoviesEntity
import com.arindom.koa2.domain.mapToDomainWrapperFlow
import com.arindom.koa2.domain.repos.IFavouriteMovies
import com.arindom.koa2.movie.data.Favourite
import kotlinx.coroutines.flow.Flow

class FavouriteMovieUseCase(
    private val favouriteMovies: IFavouriteMovies,
) {
    fun saveFavouriteMovie(movieEntity: MoviesEntity.MovieEntity) {
        favouriteMovies.saveFavoriteMovie(movieEntity.toMovie())
    }

    fun deleteFavouriteMovie(movieId: String) {
        favouriteMovies.deleteFavoriteMovie(movieId = movieId)
    }

    fun deleteAllFavourites() {
        favouriteMovies.deleteAllFavourites()
    }

    fun getAllFavouriteMovies(): Flow<DomainWrapper<MoviesEntity>> {
        return favouriteMovies.getAllFavouriteMovies()
            .mapToDomainWrapperFlow(List<Favourite>::toMoviesEntity)
    }
}