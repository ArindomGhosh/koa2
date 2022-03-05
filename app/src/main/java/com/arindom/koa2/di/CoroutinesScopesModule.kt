package com.arindom.koa2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {
    //https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
    @ApplicationScope
    @Singleton
    @Provides
    fun providesCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

}