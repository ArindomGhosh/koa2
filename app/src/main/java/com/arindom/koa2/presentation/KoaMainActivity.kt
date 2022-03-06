package com.arindom.koa2.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.arindom.koa2.presentation.navigation.AppNavigation
import com.arindom.koa2.presentation.navigation.NavigationConfigurationImpl
import com.arindom.koa2.presentation.navigation.Screen
import com.arindom.koa2.presentation.screens.moviedetails.MovieDetailScreen
import com.arindom.koa2.presentation.screens.search.MovieTabsScreen
import com.arindom.koa2.presentation.theme.Koa2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KoaMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koa2Theme {
                val navigationConfiguration = remember {
                    NavigationConfigurationImpl(homeScreen = Screen.MovieTabScreen)
                }
                var screen: Screen by rememberSaveable {
                    mutableStateOf(navigationConfiguration.currentScreen)
                }
                AppNavigation(screen = screen) { currentScreen ->
                    when (currentScreen) {
                        Screen.MovieTabScreen -> {
                            MovieTabsScreen(onMovieSelected = { movieEntity ->
                                screen =
                                    navigationConfiguration.moveToScreen(Screen.Details(movieEntity.movieId))
                            })
                        }
                        is Screen.Details -> {
                            MovieDetailScreen(
                                movieId = currentScreen.movieId,
                                onBack = {
                                    screen = navigationConfiguration.moveToPreviousScreen()
                                })
                        }
                    }
                }
            }
        }
    }
}