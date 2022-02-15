package com.arindom.koa2.presentation.screens.moviedetails

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.usecases.MovieDetailUseCase
import com.arindom.koa_mvi_core.FeatureBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailBloc @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
) :
    FeatureBloc<MovieDetailEvent, MovieDetailUiState>(MovieDetailUiState()) {
    override fun postWish(wish: MovieDetailEvent) {
        when (wish) {
            is MovieDetailEvent.MovieDetailsRequested -> getMovieDetetails(wish.movieId)
        }
    }

    private fun getMovieDetetails(movieId: String) {
        executeAsyncTask {
            postUiState(newUiState = uiState.value.isLoading(movieId = movieId))
            when (val response = movieDetailUseCase.getMovieDetailsForMovieId(movieId = movieId)) {
                is DomainWrapper.Error -> {
                    postUiState(
                        newUiState = uiState.value.onError(
                            uiError = response.uiError
                        )
                    )
                }
                is DomainWrapper.Success -> {
                    postUiState(
                        newUiState = uiState.value.onData(
                            movieDetailsEntity = response.data
                        )
                    )
                }
            }
        }
    }
}