package com.arindom.koa2.presentation.screens.search

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.usecases.MovieDetailUseCase
import com.arindom.koa2.domain.usecases.SearchMoviesUseCase
import com.arindom.koa_mvi_core.FeatureBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenFeatureBloc @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) :
    FeatureBloc<SearchScreenEvent, SearchScreenState>(SearchScreenState("Hello")) {
    override fun postWish(wish: SearchScreenEvent) {
        getMovieDetails()
        when (wish) {
            is SearchScreenEvent.Greetings -> {
                postUiState(newUiState = uiState.value.copy(message = wish.name))
            }
        }
    }

    //tt0372784
    private fun getMovieDetails() {
        executeAsyncTask {
            when (val movieDetailWrapper =
                searchMoviesUseCase.getMoviesForName("bbbbb")) {
                is DomainWrapper.Error -> {
                    println(movieDetailWrapper.uiError.message)
                }
                is DomainWrapper.Success -> {
                    println(movieDetailWrapper.data.movieList)
                }
            }
        }
    }
}