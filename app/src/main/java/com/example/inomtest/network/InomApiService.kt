package com.example.inomtest.network

import com.example.inomtest.dataClass.ProductItem
import com.example.inomtest.dataClass.LoginData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface InomApiService {
    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun loadProducts(
        @Header("Authorization") accessToken: String,
        @Query("page") page: String): Call<ProductItem>

    @POST("/api/users/login")
    fun login(
        @Header("Authorization") accessToken: String,
        @Body jsonparams: LoginData
    ): Call<Void>
}


object InomApi {
    // private const val baseUrl = "https://inu-market.cf/"  // 베이스 URL
    private const val baseUrl = "http://13.209.183.154:8080"

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