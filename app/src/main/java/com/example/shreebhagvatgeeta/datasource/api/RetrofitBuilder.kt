package com.arramton.helping.restService

import android.app.Application
import android.content.Intent
import com.example.shreebhagvatgeeta.datasource.api.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

object RetrofitBuilder {

    val header= mapOf<String,String>(
        "Accept" to "application/json",
        "X-RapidAPI-Key" to "7e5c48fdb1mshe779e1d212f795ep179de1jsnf6d9fd0493a1",
        "x-rapidapi-host" to "bhagavad-gita3.p.rapidapi.com"

    )

    val client=OkHttpClient.Builder().apply {
        addInterceptor { chain->

            val newRequest:Request=chain.request().newBuilder().apply {
                header.forEach{key,value->addHeader(key,value)}
            }.build()
            chain.proceed(newRequest)
        }
    }.build()
    val api: ApiInterface by lazy {
        Retrofit.Builder()

            .baseUrl("https://bhagavad-gita3.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) // ✅ Correct Placement
            .build()
            .create(ApiInterface::class.java)
    }



}
