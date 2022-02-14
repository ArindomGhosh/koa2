package com.arindom.koa_mvi_core.foundation

typealias ValueChange<T> = (T) -> Unit

typealias VoidCallback = () -> Unit

typealias ValueChangeAsync<T> = suspend (T) -> Unit

typealias VoidCallbackAsync = suspend () -> Unit