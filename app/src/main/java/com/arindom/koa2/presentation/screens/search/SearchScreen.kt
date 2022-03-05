package com.arindom.koa2.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arindom.koa2.R
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.widgets.KoaAlertInfo
import com.arindom.koa2.presentation.widgets.KoaLoader
import com.arindom.koa2.presentation.widgets.MovieList
import com.arindom.koa_mvi_core.foundation.ValueChange

@Composable
fun MovieSearchScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchScreenFeatureBloc: SearchScreenFeatureBloc = viewModel(),
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                val focusManager = LocalFocusManager.current
                var query: String by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = query,
                    onValueChange = { onQueryChange ->
                        query = onQueryChange
                    },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.subtitle1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                    leadingIcon = {
                        IconButton(onClick = {
                            searchScreenFeatureBloc.postWish(SearchScreenEvent.MovieQueried(query))
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            query = ""
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = "Clear Icon"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = RectangleShape,
                            color = MaterialTheme.colors.background
                        ),
                    keyboardActions = KeyboardActions(onSearch = {
                        searchScreenFeatureBloc.postWish(SearchScreenEvent.MovieQueried(query))
                        focusManager.clearFocus()
                    })
                )
                MovieSearch(
                    onMovieSelected = onMovieSelected
                )
            }
        }
    }
}


@Composable
fun MovieSearch(
    modifier: Modifier = Modifier,
    searchScreenFeatureBloc: SearchScreenFeatureBloc = viewModel(),
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    Box(modifier = modifier.fillMaxSize()) {
        val searchScreenState by searchScreenFeatureBloc.uiState.collectAsState()
        searchScreenState.uiError?.let {
            KoaAlertInfo(
                title = it.header,
                text = it.message,
            )
        }
        MovieList(
            onMovieSelected = {
                searchScreenFeatureBloc.postWish(SearchScreenEvent.OnMovieSelected(it))
                onMovieSelected(it)
            },
            data = searchScreenState.data,
        )
        if (searchScreenState.isLoading) {
            KoaLoader(
                modifier = Modifier.align(Alignment.Center),
                message = LocalContext.current.getString(
                    R.string.movie_loading
                )
            )
        }
    }
}


