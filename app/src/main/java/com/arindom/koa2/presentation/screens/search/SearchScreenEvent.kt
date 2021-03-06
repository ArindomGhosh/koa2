package com.arindom.koa2.presentation.screens.search

import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa_mvi_core.Wish

sealed class SearchScreenEvent : Wish {
    data class MovieQueried(val name: String) : SearchScreenEvent()
    data class OnMovieSelected(val movieEntity: MoviesEntity.MovieEntity) : SearchScreenEvent()
}
