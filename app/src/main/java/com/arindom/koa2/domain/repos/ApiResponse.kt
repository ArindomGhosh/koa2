package com.arindom.koa2.domain.repos

sealed class ApiResponse<Response> {
    data class Error<Response>(val throwable: Throwable) : ApiResponse<Response>()
    data class Success<Response>(val data: Response) : ApiResponse<Response>()
}
