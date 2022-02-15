package com.arindom.koa2.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arindom.koa2.R
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.theme.Koa2Theme
import com.arindom.koa2.presentation.widgets.KoaAlertInfo
import com.arindom.koa2.presentation.widgets.KoaLoader
import com.arindom.koa_mvi_core.foundation.ValueChange

@Composable
fun MovieSearchScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchScreenFeatureBloc: SearchScreenFeatureBloc,
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
                    searchScreenFeatureBloc = searchScreenFeatureBloc,
                    onMovieSelected = onMovieSelected
                )
            }
        }
    }
}


@Composable
fun MovieSearch(
    modifier: Modifier = Modifier,
    searchScreenFeatureBloc: SearchScreenFeatureBloc,
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val searchScreenState by searchScreenFeatureBloc.uiState.collectAsState()
        when {
            searchScreenState.isLoading -> KoaLoader(
                modifier = Modifier.align(Alignment.Center),
                message = LocalContext.current.getString(
                    R.string.movie_loading
                )
            )
            searchScreenState.uiError != null -> {
                KoaAlertInfo(
                    title = searchScreenState.uiError!!.header,
                    text = searchScreenState.uiError!!.message,
                )
            }
            else -> {
                searchScreenState.data?.let {
                    MovieList(
                        onMovieSelected = onMovieSelected,
                        data = it,
                    )
                }
            }
        }
    }
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>,
    data: MoviesEntity
) {
    LazyColumn(modifier = modifier) {
        items(data.movieList) {
            MovieTiles(
                onMovieSelected = onMovieSelected,
                movieEntity = it
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieTiles(
    modifier: Modifier = Modifier,
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>,
    movieEntity: MoviesEntity.MovieEntity
) {
    Card(
        modifier = modifier
            .padding(5.dp),
        onClick = { onMovieSelected(movieEntity) },
        shape = MaterialTheme.shapes.large,
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(movieEntity.moviePoster),
                    contentDescription = movieEntity.movieName,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = movieEntity.movieName)
                    Text(text = "Type: ${movieEntity.type}")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Released on: ${movieEntity.year}")
        }
    }
}

@Preview
@Composable
fun previewMovieTiles() {
    Koa2Theme(darkTheme = true) {
        MovieTiles(
            onMovieSelected = {}, movieEntity = MoviesEntity.MovieEntity(
                movieId = "tt0372784",
                movieName = "Batman Begins",
                year = "2005",
                type = "movie",
                moviePoster = "https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
        )
    }
}

