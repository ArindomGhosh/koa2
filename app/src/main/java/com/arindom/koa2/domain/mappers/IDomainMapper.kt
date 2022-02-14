package com.arindom.koa2.domain.mappers

interface IDomainMapper<SourceModel, DomainModel> {

    fun mapToDomainModel(sourceModel: SourceModel): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): SourceModel
}