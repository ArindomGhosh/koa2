package com.arindom.koa2.domain.mappers

import com.arindom.koa2.domain.entities.MoviesEntity
import com.arindom.koa2.infra.repos.responses.MovieList

class MoviesEntityDomainMapper : IDomainMapper<MovieList, MoviesEntity> {
    override fun mapToDomainModel(sourceModel: MovieList): MoviesEntity {
        return MoviesEntity(
            movieList = sourceModel.movieList?.map { movie ->
                MoviesEntity.MovieEntity(
                    movieId = movie.movieId,
                    movieName = movie.title,
                    moviePoster = movie.poster,
                    year = movie.year,
                    type = movie.type
                )
            }?.toList() ?: emptyList()
        )
    }

    override fun mapFromDomainModel(domainModel: MoviesEntity): MovieList {
        return MovieList(
            isValidResponse = true,
            movieList = domainModel.movieList.map { movieEntity ->
                MovieList.Movie(
                    title = movieEntity.movieName,
                    year = movieEntity.year,
                    movieId = movieEntity.movieId,
                    poster = movieEntity.moviePoster,
                    type = movieEntity.type
                )
            }.toList(),
            errorMessage = null
        )
    }
}