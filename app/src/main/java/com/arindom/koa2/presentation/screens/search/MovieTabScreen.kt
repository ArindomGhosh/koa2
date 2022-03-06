package com.arindom.koa2.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.screens.favourite.FavouriteScreen
import com.arindom.koa_mvi_core.foundation.ValueChange

enum class Tabs{
    Search,
    Favourites
}

@Composable
fun MovieTabsScreen(
    modifier: Modifier = Modifier,
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    Column(modifier = modifier) {
        var selectedTab by rememberSaveable { mutableStateOf(0) }
        val tabs = listOf(Tabs.Search, Tabs.Favourites)
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.height(50.dp)
        ) {
            tabs.forEachIndexed { index, element ->
                Tab(selected = index == selectedTab, onClick = { selectedTab = index }) {
                    Text(
                        text = element.name,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    )
                }
            }
        }
        when (tabs[selectedTab]) {
            Tabs.Search ->  MovieSearchScreen(
                onMovieSelected = onMovieSelected
            )
            Tabs.Favourites -> FavouriteScreen(onMovieSelected = onMovieSelected)
        }

    }
}