package com.arindom.koa2.di

import com.arindom.koa2.infra.services.retrofit.IServiceCreator
import com.arindom.koa2.infra.services.retrofit.ServiceCreator
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