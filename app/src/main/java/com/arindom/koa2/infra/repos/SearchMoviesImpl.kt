package com.arindom.koa2.infra.repos

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.exceptions.NoDataFoundException
import com.arindom.koa2.infra.repos.responses.MovieDetail
import com.arindom.koa2.infra.repos.responses.MovieList
import com.arindom.koa2.infra.services.IServiceCreator
import com.arindom.koa2.infra.utils.returnServiceFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchMoviesImpl(
    private val serviceCreator: IServiceCreator,
    private val ioDispatcher: CoroutineDispatcher
) : ISearchMovie {
    override fun getMovies(movieName: String): Flow<ApiResponse<MovieList>> {
        return returnServiceFlow({ serviceCreator.omdbService.searchMovies(movieName) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieName))
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>> {
        return returnServiceFlow({ serviceCreator.omdbService.getMovieDetails(movieId = movieId) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieId))
            }
        }.flowOn(ioDispatcher)
    }
}