package com.arindom.koa2.presentation.navigation

import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {
    @Parcelize
    object MovieTabScreen : Screen()

    @Parcelize
    data class Details(val movieId: String) : Screen()
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    screen: Screen,
    content: @Composable (Screen) -> Unit
) {
    val saveableStateHolder = rememberSaveableStateHolder()
//    CompositionLocalProvider(
//        LocalNavigationConfiguration provides NavigationConfiguration()
//    ) {
    //https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/SaveableStateHolder#SaveableStateProvider(kotlin.Any,kotlin.Function0)
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        saveableStateHolder.SaveableStateProvider(key = screen) {
            println(screen.hashCode())
            content(screen)
        }
    }
//    }
}

interface NavigationConfiguration {
    val currentScreen: Screen
    fun moveToPreviousScreen(): Screen?
    fun moveToScreen(screen: Screen): Screen
}

class NavigationConfigurationImpl(homeScreen: Screen) :
    NavigationConfiguration {
    private val screenStack = Stack<Screen>()
    init {
        screenStack.push(homeScreen)
    }
    override val currentScreen: Screen
        get() = screenStack.peek()!!

    override fun moveToPreviousScreen(): Screen {
        if (screenStack.size() >= 2 && screenStack.peek() != Screen.MovieTabScreen) {
            screenStack.pop()
        }
        return screenStack.peek()!!
    }

    override fun moveToScreen(screen: Screen): Screen {
        screenStack.push(screen)
        return screen
    }

}

//Not a good idea to use for Navigation
val LocalNavigationConfiguration = staticCompositionLocalOf<NavigationConfiguration> {
    noLocalNavigationConfigurationProvided()
}

private fun noLocalNavigationConfigurationProvided(): Nothing {
    throw IllegalArgumentException("No Navigation Config provided")
}


class Stack<T : Any> {
    private val stack = mutableListOf<T>()

    fun push(screen: T) {
        stack.add(screen)
    }

    fun pop(): T? {
        if (stack.isNotEmpty()) {
            return stack.removeAt(stack.lastIndex)
        }
        return null
    }

    fun peek(): T? {
        return stack.lastOrNull()
    }

    fun isEmpty() = stack.isEmpty()

    fun size() = stack.size
}
