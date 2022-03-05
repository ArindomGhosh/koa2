package com.arindom.koa2.presentation.screens.search

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.usecases.FavouriteMovieUseCase
import com.arindom.koa2.domain.usecases.SearchMoviesUseCase
import com.arindom.koa_mvi_core.FeatureBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchScreenFeatureBloc @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val favouriteMovieUseCase: FavouriteMovieUseCase

) : FeatureBloc<SearchScreenEvent, SearchScreenState>(SearchScreenState()) {
    override fun postWish(wish: SearchScreenEvent) {
        when (wish) {
            is SearchScreenEvent.MovieQueried -> {
                getMovies(wish.name)
            }
            is SearchScreenEvent.OnMovieSelected -> {
                favouriteMovieUseCase.saveFavouriteMovie(wish.movieEntity)
            }
        }
    }

    private fun getMovies(name: String) {
        executeAsyncTask {
            postUiState(newUiState = uiState.value.isLoading(name))
            when (val movieDetailWrapper =
                searchMoviesUseCase.getMoviesForName(name)) {
                is DomainWrapper.Error -> {
                    postUiState(
                        uiState.value.onError(
                            uiError = movieDetailWrapper.uiError
                        )
                    )
                }
                is DomainWrapper.Success -> {
                    postUiState(
                        uiState.value.onData(
                            moviesEntity = movieDetailWrapper.data,
                        )
                    )
                }
            }
        }
    }
}