package com.arindom.koa2.domain.mappers

import com.arindom.koa2.domain.entities.MovieDetailsEntity
import com.arindom.koa2.infra.repos.responses.MovieDetail

class MoviesDetailEntityMapper : IDomainMapper<MovieDetail, MovieDetailsEntity> {
    override fun mapToDomainModel(sourceModel: MovieDetail): MovieDetailsEntity {
        return MovieDetailsEntity(
            movieId = sourceModel.movieId!!,
            movieName = sourceModel.title!!,
            releaseYear = sourceModel.year!!,
            genre = sourceModel.genre!!,
            director = sourceModel.director!!,
            actors = sourceModel.actors?.split(", ") ?: emptyList(),
            plot = sourceModel.plot!!,
            poster = sourceModel.poster!!,
            language = sourceModel.language!!,
            awards = sourceModel.awards?.split(". ") ?: emptyList(),
            rate = sourceModel.rated!!,
            releaseDate = sourceModel.released!!,
            runTime = sourceModel.runtime!!
        )
    }

    override fun mapFromDomainModel(domainModel: MovieDetailsEntity): MovieDetail {
        return MovieDetail(
            isValidResponse = true,
            title = domainModel.movieName,
            year = domainModel.releaseYear,
            rated = domainModel.rate,
            released = domainModel.releaseDate,
            poster = domainModel.poster,
            plot = domainModel.plot,
            actors = domainModel.actors.joinToString(", "),
            director = domainModel.director,
            genre = domainModel.genre,
            awards = domainModel.awards.joinToString(". "),
            errorMessage = null,
            language = domainModel.language,
            movieId = domainModel.movieId,
            runtime = domainModel.runTime
        )
    }
}