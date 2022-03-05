package com.arindom.koa2.infra.local.database.dao

import android.util.Log
import com.arindom.koa2.KaoDatabase
import com.arindom.koa2.infra.repos.responses.serialization.MovieList
import com.arindom.koa2.movie.data.Favourite
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FavouriteDaoImpl(
    private val db: KaoDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : FavouriteDao {
    override suspend fun insertFavourite(vararg movie: MovieList.Movie) {
        withContext(ioDispatcher) {
            db.favouriteQueries.transaction {
                afterCommit { Log.d("FavouriteDao", "insertFavourite: success") }
                afterRollback { Log.d("FavouriteDao", "insertFavourite: failure") }
                movie.forEach {
                    db.favouriteQueries.insertFavourite(
                        Uid = it.movieId,
                        name = it.title,
                        type = it.type,
                        releaseYear = it.year,
                        poster = it.poster
                    )
                }
            }
        }
    }

    override fun getAllFavourites(): Flow<List<Favourite>> {
        return db.favouriteQueries.getAllFavourite()
            .asFlow()
            .mapToList(ioDispatcher)
    }

    override suspend fun getFavouriteById(uid: String): Favourite? {
        return withContext(ioDispatcher) {
            db.favouriteQueries.getFavouriteByUId(uid = uid).executeAsOneOrNull()
        }
    }

    override suspend fun deleteAllFavourite() {
        withContext(ioDispatcher) {
            db.favouriteQueries.deleteAll()
        }
    }

    override suspend fun deleteFavouriteById(uid: String) {
        withContext(ioDispatcher) {
            db.favouriteQueries.deleteFavouriteForUid(uid = uid)
        }
    }
}