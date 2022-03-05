package com.arindom.koa2.infra.utils

import com.arindom.koa2.domain.repos.ApiResponse
import com.arindom.koa2.exceptions.ApiExceptions
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> returnRetroServiceFlow(
    serviceRequest: suspend () -> Response<T>,
    action: (T) -> ApiResponse<T>
): Flow<ApiResponse<T>> {
    return flow<ApiResponse<T>> {
        kotlin.runCatching {
            serviceRequest()
        }.onSuccess { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(action(it))
                } ?: kotlin.run {
                    emit(ApiResponse.Error(ApiExceptions()))
                }
            } else {
                emit(ApiResponse.Error(ApiExceptions()))
            }
        }.onFailure {
            emit(ApiResponse.Error(ApiExceptions()))
        }
    }
}

fun <T> returnKtorServiceFlow(
    serviceRequest: suspend () -> HttpResponse,
    action: (String) -> ApiResponse<T>
): Flow<ApiResponse<T>> {
    return flow<ApiResponse<T>> {
        kotlin.runCatching {
            serviceRequest.invoke()
        }.onSuccess { response ->
            if (response.status.isSuccess()) {
                emit(action(response.receive()))
            } else {
                emit(ApiResponse.Error(ApiExceptions()))
            }
        }.onFailure {
            emit(ApiResponse.Error(ApiExceptions()))
        }

    }
}