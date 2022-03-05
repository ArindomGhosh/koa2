package com.arindom.koa2.infra.repos

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.domain.repos.ISearchMovieKtor
import com.arindom.koa2.exceptions.NoDataFoundException
import com.arindom.koa2.infra.local.database.dao.FavouriteDao
import com.arindom.koa2.infra.repos.responses.serialization.MovieDetail
import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.infra.services.ktor.OmdbServices
import com.arindom.koa2.infra.utils.returnKtorServiceFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SearchMoviesKtorImpl(
    private val omdbServices: OmdbServices,
    private val favouriteDao: FavouriteDao,
    private val coroutineScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher
) : ISearchMovieKtor {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    override fun getMovies(movieName: String): Flow<ApiResponse<MovieList>> {
        return returnKtorServiceFlow({ omdbServices.searchMovies(movieName) }) {
            val movieList =
                json.decodeFromString<MovieList>(it)
            if (movieList.isValidResponse) {
                ApiResponse.Success(movieList)
            } else {
                ApiResponse.Error(NoDataFoundException(movieName))
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>> {
        return returnKtorServiceFlow({ omdbServices.getMoviesDetails(movieId) }) {
            val movieDetail =
                json.decodeFromString<MovieDetail>(it)
            if (movieDetail.isValidResponse) {
                ApiResponse.Success(movieDetail)
            } else {
                ApiResponse.Error(NoDataFoundException(movieId))
            }
        }.flowOn(ioDispatcher)
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

}