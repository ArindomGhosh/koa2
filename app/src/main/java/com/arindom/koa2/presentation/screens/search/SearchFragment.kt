package com.arindom.koa2.presentation.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.arindom.koa2.presentation.theme.Koa2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchScreenFeatureBloc: SearchScreenFeatureBloc by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
                setContent {
                    Koa2Theme {
                        MovieSearchScreen(
                            searchScreenFeatureBloc = searchScreenFeatureBloc
                        ) {
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


