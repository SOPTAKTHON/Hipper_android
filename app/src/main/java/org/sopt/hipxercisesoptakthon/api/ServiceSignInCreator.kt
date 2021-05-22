package org.sopt.hipxercisesoptakthon.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceSignInCreator {
    private const val BASE_URL = "http://54.180.153.139:5000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    // Set Log Level of OkHttp
    val interceptor = HttpLoggingInterceptor()
    val client = OkHttpClient().newBuilder()
        .addNetworkInterceptor(interceptor)
        .build()
    val signInService: SignInService = retrofit.create(SignInService::class.java)
}