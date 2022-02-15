package com.arindom.koa2.di

import com.arindom.koa2.infra.services.IServiceCreator
import com.arindom.koa2.infra.services.ServiceCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Singleton
    @Provides
    fun provideServiceCreator(): IServiceCreator {
        return ServiceCreator()
    }
}