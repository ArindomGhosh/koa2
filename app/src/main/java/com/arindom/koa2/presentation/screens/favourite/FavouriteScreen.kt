package com.arindom.koa2.presentation.screens.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arindom.koa2.R
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.widgets.KoaAlertInfo
import com.arindom.koa2.presentation.widgets.KoaLoader
import com.arindom.koa2.presentation.widgets.MovieList
import com.arindom.koa_mvi_core.foundation.ValueChange

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    favouriteFeatureBloc: FavouriteFeatureBloc = viewModel(),
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    favouriteFeatureBloc.postWish(FavouriteEvents.GetAllFavourites)
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                favouriteFeatureBloc.postWish(FavouriteEvents.DeleteAllFavourites)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete All Fav")
            }
        },
        floatingActionButtonPosition = FabPosition.End,

    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val favouriteUiState by favouriteFeatureBloc.uiState.collectAsState()
            if (favouriteUiState.isLoading) {
                KoaLoader(
                    modifier = Modifier.align(Alignment.Center),
                    message = LocalContext.current.getString(
                        R.string.movie_loading
                    )
                )
            }
            favouriteUiState.uiError?.let { err ->
                KoaAlertInfo(
                    title = err.header,
                    text = err.message,
                )
            }
            MovieList(
                onMovieSelected = onMovieSelected,
                data = favouriteUiState.data
            )
        }
    }
}