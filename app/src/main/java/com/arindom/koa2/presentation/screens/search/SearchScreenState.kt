package com.arindom.koa2.presentation.screens.search

import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.domain.entities.UiError
import com.arindom.koa_mvi_core.UiState

data class SearchScreenState(
    val query: String = "",
    val data: MoviesEntity = MoviesEntity(emptyList()),
    val isLoading: Boolean = false,
    val uiError: UiError? = null
) : UiState

fun SearchScreenState.isLoading(query: String) = this.copy(isLoading = true, query = query)

fun SearchScreenState.onError(uiError: UiError) = this.copy(
    isLoading = false,
    uiError = uiError
)

fun SearchScreenState.onData(moviesEntity: MoviesEntity) = this.copy(
    isLoading = false,
    data = moviesEntity,
    uiError = null
)