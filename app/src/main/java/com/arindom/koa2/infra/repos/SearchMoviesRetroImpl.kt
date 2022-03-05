package com.arindom.koa2.infra.repos

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.exceptions.NoDataFoundException
import com.arindom.koa2.infra.repos.responses.gson.MovieDetail
import com.arindom.koa2.infra.repos.responses.gson.MovieList
import com.arindom.koa2.infra.services.retrofit.IServiceCreator
import com.arindom.koa2.infra.utils.returnRetroServiceFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchMoviesRetroImpl(
    private val serviceCreator: IServiceCreator,
    private val ioDispatcher: CoroutineDispatcher
) : ISearchMovie {
    override fun getMovies(movieName: String): Flow<ApiResponse<MovieList>> {
        return returnRetroServiceFlow({ serviceCreator.omdbService.searchMovies(movieName) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieName))
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>> {
        return returnRetroServiceFlow({ serviceCreator.omdbService.getMovieDetails(movieId = movieId) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieId))
            }
        }.flowOn(ioDispatcher)
    }
}