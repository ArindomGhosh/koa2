package com.arindom.koa2.domain.entities

import com.arindom.koa2.exceptions.ApiExceptions
import com.arindom.koa2.exceptions.NoDataFoundException

data class UiError(
    val header: String,
    val message: String,
    val isActionRequired: Boolean = false
)

fun Throwable.toUiError(): UiError {
    return when (this) {
        is ApiExceptions -> UiError(
            header = "Technical Issue",
            message = this.message!!
        )
        is NoDataFoundException -> UiError(
            header = "No Movie Found",
            message = "No Movie found with name ${this.message}"
        )
        else -> UiError(
            header = "Technical Issue",
            message = "Something went wrong, try again",
            isActionRequired = true
        )
    }
}