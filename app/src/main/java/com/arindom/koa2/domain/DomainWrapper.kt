package com.arindom.koa2.domain

import com.arindom.koa2.domain.entities.UiError
import com.arindom.koa2.domain.entities.toUiError
import com.arindom.koa2.domain.repos.ApiResponse


sealed class DomainWrapper<DomainModel> {
    data class Success<DomainModel>(val data: DomainModel) : DomainWrapper<DomainModel>()
    data class Error<DomainModel>(val uiError: UiError) : DomainWrapper<DomainModel>()
}

fun <ResponseModel, DomainModel> ApiResponse<ResponseModel>.mapToDomainWrapper(
    domainMapper: ResponseModel.() -> DomainModel
): DomainWrapper<DomainModel> {
    return when (this) {
        is ApiResponse.Error -> DomainWrapper.Error(this.throwable.toUiError())
        is ApiResponse.Success ->  DomainWrapper.Success(this.data.domainMapper())
    }
}

fun <R, DomainModel> DomainWrapper<DomainModel>.map(block: (DomainWrapper<DomainModel>) -> R): R {
    return block(this)
}