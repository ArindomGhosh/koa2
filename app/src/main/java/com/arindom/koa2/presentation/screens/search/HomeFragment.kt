package com.arindom.koa2.presentation.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.presentation.screens.favourite.FavouriteScreen
import com.arindom.koa2.presentation.theme.Koa2Theme
import com.arindom.koa_mvi_core.foundation.ValueChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
                setContent {
                    Koa2Theme {
                        MovieTabs {
                            findNavController()
                                .navigate(
                                    directions = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(
                                        it.movieId
                                    )
                                )
                        }
                    }
                }
            }
    }
}

@Composable
fun MovieTabs(
    modifier: Modifier = Modifier,
    onMovieSelected: ValueChange<MoviesEntity.MovieEntity>
) {
    Column(modifier = modifier) {
        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Search", "Favourites")
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.height(50.dp)
        ) {
            tabs.forEachIndexed { index, element ->
                Tab(selected = index == selectedTab, onClick = { selectedTab = index }) {
                    Text(
                        text = element,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    )
                }
            }
        }
        when (tabs[selectedTab]) {
            "Search" -> {
                MovieSearchScreen(
                    onMovieSelected = onMovieSelected
                )
            }
            "Favourites" -> FavouriteScreen(onMovieSelected = onMovieSelected)
        }

    }
}


