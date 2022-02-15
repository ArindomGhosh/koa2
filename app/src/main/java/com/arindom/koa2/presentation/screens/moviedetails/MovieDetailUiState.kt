package com.arindom.koa2.presentation.screens.moviedetails

import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.domain.entities.UiError
import com.arindom.koa_mvi_core.UiState

data class MovieDetailUiState(
    val requestedMovieId: String = "",
    val movieDetailsEntity: MovieDetailsEntity? = null,
    val isLoading: Boolean = false,
    val uiError: UiError? = null
) : UiState

fun MovieDetailUiState.isLoading(movieId: String) =
    this.copy(isLoading = true, requestedMovieId = movieId)

fun MovieDetailUiState.onError(uiError: UiError) = this.copy(isLoading = false, uiError = uiError)

fun MovieDetailUiState.onData(movieDetailsEntity: MovieDetailsEntity) =
    this.copy(isLoading = false, uiError = null, movieDetailsEntity = movieDetailsEntity)