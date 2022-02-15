package com.arindom.koa2.di

import android.content.Context
import androidx.room.Room
import com.arindom.koa2.infra.local.database.KaoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRoomDb(
        @ApplicationContext context: Context
    ): KaoDatabase {
        return Room.databaseBuilder(
            context,
            KaoDatabase::class.java,
            "kao_database"
        ).build()
    }
}