package com.arindom.koa2.presentation.features.search

import com.arindom.koa_mvi_core.FeatureBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenFeatureBloc @Inject constructor() :
    FeatureBloc<SearchScreenEvent, SearchScreenState>(SearchScreenState("Hello")) {
    override fun postWish(wish: SearchScreenEvent) {
        when (wish) {
            is SearchScreenEvent.Greetings -> {
                postUiState(newUiState = uiState.value.copy(message = wish.name))
            }
        }
    }
}