package com.arindom.koa2.domain.entities

data class UiError(
    val header: String,
    val message:String,
    val isActionRequired:Boolean = false
)

fun Throwable.toUiError():UiError{
    return UiError(
        header = "Technical Issue",
        message = "Something went wrong, try again",
        isActionRequired = true
    )
}