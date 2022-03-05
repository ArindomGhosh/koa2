package com.arindom.koa2.infra.repos

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.domain.repos.IFavouriteMovies
import com.arindom.koa2.exceptions.NoFavouriteFound
import com.arindom.koa2.infra.local.database.dao.FavouriteDao
import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.movie.data.Favourite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavouriteMoviesImpl(
    private val favouriteDao: FavouriteDao,
    private val coroutineScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher
) : IFavouriteMovies {
    override fun getAllFavouriteMovies(): Flow<ApiResponse<List<Favourite>>> {
        return favouriteDao.getAllFavourites()
            .map { favourites ->
                if (favourites.isEmpty()) {
                    ApiResponse.Error(NoFavouriteFound)
                } else {
                    ApiResponse.Success(favourites)
                }
            }
            .flowOn(ioDispatcher)
    }

    override fun saveFavoriteMovie(movie: MovieList.Movie) {
        coroutineScope.launch {
            favouriteDao.insertFavourite(movie = arrayOf(movie))
        }
    }

    override fun deleteFavoriteMovie(movieId: String) {
        coroutineScope.launch {
            favouriteDao.deleteFavouriteById(movieId)
        }
    }

    override fun deleteAllFavourites() {
        coroutineScope.launch {
            favouriteDao.deleteAllFavourite()
        }
    }
}