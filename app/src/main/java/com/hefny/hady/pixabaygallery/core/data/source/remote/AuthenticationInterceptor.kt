package com.hefny.hady.pixabaygallery.core.data.source.remote

import com.hefny.hady.pixabaygallery.core.keys.PixabayKeys
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url
        val newHttpUrl: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("key", PixabayKeys.getApiKey())
            .build()
        val newRequest: Request = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        return chain.proceed(newRequest)
    }
}