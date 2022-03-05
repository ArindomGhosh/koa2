package com.arindom.koa2.presentation.screens.favourite

import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.entities.UiError
import com.arindom.koa_mvi_core.UiState

data class FavouriteScreenState(
    val data: MoviesEntity = MoviesEntity(emptyList()),
    val isLoading: Boolean = false,
    val uiError: UiError? = null
) : UiState

fun FavouriteScreenState.isLoading() = this.copy(
    isLoading = true
)

fun FavouriteScreenState.onError(uiError: UiError) = this.copy(
    isLoading = false,
    uiError = uiError,
    data = MoviesEntity(emptyList())
)

fun FavouriteScreenState.onSuccess(moviesEntity: MoviesEntity) = this.copy(
    isLoading = false,
    uiError = null,
    data = moviesEntity
)
