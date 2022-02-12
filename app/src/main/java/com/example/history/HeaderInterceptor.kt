package com.example.history

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class HeaderInterceptor constructor(private val token : String) : Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer $token"
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorizaion", token)
            .build()
        return chain.proceed(newRequest)
    }
}