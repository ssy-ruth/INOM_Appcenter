package com.example.inomtest.network

import com.example.inomtest.dataClass.ProductItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface InomApiService {
    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun loadProducts(
        @Header("Authorization") accessToken: String,
        @Query("page") page: String): Call<ProductItem>
}

object InomApi {
    // private const val baseUrl = "https://inu-market.cf/"  // 베이스 URL
    private const val baseUrl = "http://54.180.73.82:8081"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi(): InomApiService {
        return retrofit.create(
            InomApiService::class.java
        )
    }
}