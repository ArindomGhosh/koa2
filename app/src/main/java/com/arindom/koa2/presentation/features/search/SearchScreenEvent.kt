package com.arindom.koa2.presentation.features.search

import com.arindom.koa_mvi_core.Wish

sealed class SearchScreenEvent : Wish {
    data class Greetings(val name: String) : SearchScreenEvent()
}
