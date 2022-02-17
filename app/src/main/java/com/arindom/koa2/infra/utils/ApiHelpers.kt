package com.arindom.koa2.infra.utils

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.exceptions.ApiExceptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> returnServiceFlow(
    serviceRequest: suspend () -> Response<T>,
    action: (T) -> ApiResponse<T>
): Flow<ApiResponse<T>> {
    return flow<ApiResponse<T>> {
        kotlin.runCatching {
            serviceRequest()
        }.onSuccess { response ->
            response.body()?.let {
                emit(action(it))
            } ?: kotlin.run {
                emit(ApiResponse.Error(ApiExceptions()))
            }
        }.onFailure {
            emit(ApiResponse.Error(ApiExceptions()))
        }
    }
        .flowOn(Dispatchers.IO)
}