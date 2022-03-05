package com.arindom.koa2.domain

import com.arindom.koa2.domain.entities.UiError
import com.arindom.koa2.domain.entities.toUiError
import com.arindom.koa2.domain.repos.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


sealed class DomainWrapper<DomainModel> {
    data class Success<DomainModel>(val data: DomainModel) : DomainWrapper<DomainModel>()
    data class Error<DomainModel>(val uiError: UiError) : DomainWrapper<DomainModel>()
}

fun <ResponseModel, DomainModel> ApiResponse<ResponseModel>.mapToDomainWrapper(
    domainMapper: ResponseModel.() -> DomainModel
): DomainWrapper<DomainModel> {
    return when (this) {
        is ApiResponse.Error -> DomainWrapper.Error(this.throwable.toUiError())
        is ApiResponse.Success -> DomainWrapper.Success(this.data.domainMapper())
    }
}

fun <ResponseModel, DomainModel> Flow<ApiResponse<ResponseModel>>.mapToDomainWrapperFlow(
    domainMapper: ResponseModel.() -> DomainModel
): Flow<DomainWrapper<DomainModel>> {
    return this.map {
        when (it) {
            is ApiResponse.Error -> DomainWrapper.Error(it.throwable.toUiError())
            is ApiResponse.Success -> DomainWrapper.Success(it.data.domainMapper())
        }
    }
}

fun <R, DomainModel> DomainWrapper<DomainModel>.map(block: (DomainWrapper<DomainModel>) -> R): R {
    return block(this)
}