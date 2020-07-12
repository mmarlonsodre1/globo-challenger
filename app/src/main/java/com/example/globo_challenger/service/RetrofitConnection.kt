package com.example.globo_challenger.service

import com.example.globo_challenger.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConnection() {
    var retrofit: Retrofit? = null
    val BASE_API_URL = "https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/"

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .apply { if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor) }
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}