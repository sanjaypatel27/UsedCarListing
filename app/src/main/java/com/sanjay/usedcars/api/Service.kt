package com.sanjay.usedcars.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private const val HOST = "https://carfax-for-consumers.firebaseio.com/"
    const val TAG = "CARFAX"
    val retrofit: Retrofit by lazy {
        val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        retrofit
    }
    private val httpClient: OkHttpClient.Builder by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(logging)
        httpClient
    }


}
