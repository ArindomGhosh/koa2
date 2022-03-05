package com.arindom.koa2.presentation.screens.favourite

import com.arindom.koa2.domain.DomainWrapper
import com.arindom.koa2.domain.usecases.FavouriteMovieUseCase
import com.arindom.koa_mvi_core.FeatureBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteFeatureBloc @Inject constructor(
    private val favouriteMovieUseCase: FavouriteMovieUseCase
) :
    FeatureBloc<FavouriteEvents, FavouriteScreenState>(FavouriteScreenState()) {

    override fun postWish(wish: FavouriteEvents) {
        when (wish) {
            FavouriteEvents.GetAllFavourites -> getAllFavourites()
            is FavouriteEvents.OnFavouriteMovieDelete -> deleteFavorite(wish.movieId)
            FavouriteEvents.DeleteAllFavourites -> favouriteMovieUseCase.deleteAllFavourites()
        }
    }

    private fun getAllFavourites() {
        executeAsyncTask {
            postUiState(
                newUiState = uiState.value.isLoading()
            )
            favouriteMovieUseCase.getAllFavouriteMovies()
                .collect {
                    when (it) {
                        is DomainWrapper.Error -> {
                            postUiState(
                                newUiState = uiState.value.onError(
                                    uiError = it.uiError
                                )
                            )
                        }
                        is DomainWrapper.Success -> {
                            postUiState(
                                newUiState = uiState.value.onSuccess(moviesEntity = it.data)
                            )
                        }
                    }

                }
        }
    }

    private fun deleteFavorite(movieId: String) {
        executeAsyncTask {
            favouriteMovieUseCase.deleteFavouriteMovie(movieId = movieId)
        }
    }
}