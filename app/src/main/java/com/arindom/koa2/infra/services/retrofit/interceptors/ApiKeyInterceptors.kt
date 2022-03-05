package com.arindom.koa2.infra.services.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class   ApiKeyInterceptors : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url
        val newRequest = original.newBuilder()
            .url(
                originalUrl.newBuilder()
                    .addQueryParameter("apikey", "2f7b1663")
                    .build()
            )
            .build()
        return chain.proceed(newRequest)
    }
}