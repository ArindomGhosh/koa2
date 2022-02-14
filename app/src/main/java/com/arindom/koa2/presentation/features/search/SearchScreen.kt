package com.arindom.koa2.presentation.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arindom.koa2.presentation.theme.Koa2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private val searchScreenFeatureBloc: SearchScreenFeatureBloc by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchScreenFeatureBloc.postWish(SearchScreenEvent.Greetings("Arindom"))
        return ComposeView(requireContext())
            .apply {
                setContent {
                    Koa2Theme {
                        val homeScreenState = searchScreenFeatureBloc.uiState.collectAsState()
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Greeting(homeScreenState.value.message)
                        }
                    }
                }
            }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Koa2Theme {
        Greeting("Android")
    }
}