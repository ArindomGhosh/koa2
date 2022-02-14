package com.arindom.koa2.infra.repos

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.domain.repos.ISearchMovie
import com.arindom.koa2.domain.repos.responses.MovieDetail
import com.arindom.koa2.domain.repos.responses.MovieList
import com.arindom.koa2.exceptions.NoDataFoundException
import com.arindom.koa2.infra.services.OmdbServices
import com.arindom.koa2.infra.utils.returnServiceFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesImpl @Inject constructor(
    private val omdbServices: OmdbServices
) : ISearchMovie {
    override fun getMovies(movieName: String): Flow<ApiResponse<MovieList>> {
        return returnServiceFlow({ omdbServices.searchMovies(movieName) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieName))
            }
        }

        /*flow<ApiResponse<MovieList>>{
               val response = omdbServices.searchMovies(movieName)
               if (response.isSuccessful){
                   response.body()?.let {
                       if (it.isValidResponse){
                           emit(ApiResponse.Success(it))
                       }else{
                           emit(ApiResponse.Error(NoDataFoundException(movieName)))
                       }
                   }?: kotlin.run {
                       emit(ApiResponse.Error(ApiExceptions()))
                   }
               }else{
                   emit(ApiResponse.Error(ApiExceptions()))
               }
           }*/
    }

    override fun getMovieDetails(movieId: String): Flow<ApiResponse<MovieDetail>> {
        return returnServiceFlow({ omdbServices.getMovieDetails(movieId = movieId) }) {
            if (it.isValidResponse) {
                ApiResponse.Success(it)
            } else {
                ApiResponse.Error(NoDataFoundException(movieId))
            }
        }
    }
}