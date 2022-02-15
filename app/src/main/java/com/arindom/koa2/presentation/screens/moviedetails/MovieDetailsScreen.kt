package com.arindom.koa2.presentation.screens.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.arindom.koa2.R
import com.arindom.koa2.presentation.widgets.KoaAlertInfo
import com.arindom.koa2.presentation.widgets.KoaLoader

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    movieDetailBloc: MovieDetailBloc,
    movieId: String
) {
    movieDetailBloc.postWish(MovieDetailEvent.MovieDetailsRequested(movieId = movieId))
    Scaffold(
        modifier = modifier.padding(16.dp),
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val movieDetailUiState by movieDetailBloc.uiState.collectAsState()
            MovieDetail(
                movietailUiState = movieDetailUiState
            )
        }
    }
}

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier,
    movietailUiState: MovieDetailUiState
) {
    when {
        movietailUiState.isLoading -> KoaLoader(message = LocalContext.current.getString(R.string.load_details_message))
        movietailUiState.uiError != null -> KoaAlertInfo(
            title = movietailUiState.uiError.header,
            text = movietailUiState.uiError.message
        )
        else -> {
            movietailUiState.movieDetailsEntity?.let {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(it.poster),
                            contentDescription = it.movieName,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = it.movieName, style = TextStyle(
                                    fontFamily = FontFamily.Cursive,
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Directed by ${it.director}", style = TextStyle(
                                    fontFamily = FontFamily.Cursive,
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Released: ${it.releaseYear}", style = TextStyle(
                                    fontFamily = FontFamily.Cursive,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Plot:",
                        style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = it.plot,
                        style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Actors",
                        style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    it.actors.forEach {
                        Text(
                            text = it,
                            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }
        }
    }
}