package com.arindom.koa2.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.theme.Koa2Theme
import com.arindom.koa_mvi_core.foundation.ValueChange

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
        onClick = {
            onMovieSelected(movieEntity)
        },
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