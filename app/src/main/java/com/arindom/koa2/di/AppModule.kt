package com.arindom.koa2.di

import android.content.Context
import com.arindom.koa2.KaoDatabase
import com.arindom.koa2.infra.local.database.dao.FavouriteDao
import com.arindom.koa2.infra.local.database.dao.FavouriteDaoImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesSqlDriver(
        @ApplicationContext app: Context
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = KaoDatabase.Schema,
            context = app,
            name = "kao.db"
        )
    }

    @Singleton
    @Provides
    fun provideFavouriteDao(
        sqliteDriver: SqlDriver,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): FavouriteDao {
        return FavouriteDaoImpl(
            KaoDatabase(sqliteDriver),
            coroutineDispatcher
        )
    }
}