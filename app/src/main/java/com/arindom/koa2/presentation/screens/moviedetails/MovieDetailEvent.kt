package com.arindom.koa2.presentation.screens.moviedetails

import com.arindom.koa_mvi_core.Wish

sealed class MovieDetailEvent : Wish {
    data class MovieDetailsRequested(val movieId: String) : MovieDetailEvent()
}