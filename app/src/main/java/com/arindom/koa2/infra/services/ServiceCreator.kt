package com.arindom.koa2.infra.services

import com.arindom.koa2.infra.services.interceptors.ApiKeyInterceptors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit(url: String): Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptors())
        .addInterceptor(httpLoggingInterceptor)
        .build()
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()
}

class ServiceCreator : IServiceCreator {
    private val retrofit = getRetrofit("https://www.omdbapi.com")
    override val omdbService: OmdbServices = retrofit.create(OmdbServices::class.java)
}