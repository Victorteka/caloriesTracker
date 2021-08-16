package com.victorteka.data.remote.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("x-app-id", "150a6b66")
            .header("x-app-key", "8559e5307423770d4f656d03fec71436")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}