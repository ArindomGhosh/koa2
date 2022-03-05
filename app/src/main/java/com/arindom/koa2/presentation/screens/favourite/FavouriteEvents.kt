package com.arindom.koa2.presentation.screens.favourite

import com.arindom.koa_mvi_core.Wish

sealed class FavouriteEvents : Wish {
    data class OnFavouriteMovieDelete(val movieId: String) : FavouriteEvents()
    object GetAllFavourites : FavouriteEvents()
    object DeleteAllFavourites : FavouriteEvents()
}