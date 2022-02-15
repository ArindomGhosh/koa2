package com.arindom.koa2.presentation.screens.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arindom.koa2.presentation.theme.Koa2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val argument by navArgs<MovieDetailsFragmentArgs>()
    private val movieDetailBloc: MovieDetailBloc by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                Koa2Theme {
                    MovieDetail(
                        movieDetailBloc = movieDetailBloc,
                        movieId = argument.movieId
                    )
                }
            }
        }
    }
}

