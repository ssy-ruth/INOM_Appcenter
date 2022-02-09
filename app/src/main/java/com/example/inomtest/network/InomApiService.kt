package com.example.inomtest.network

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.room.Room
import com.example.inomtest.BuildConfig
import com.example.inomtest.RecentSearchDatabase
import com.example.inomtest.dataClass.ProductItem
import com.example.inomtest.dataClass.LoginData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

interface InomApiService {
    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun loadProducts(
        @Header("Authorization") accessToken: String,
        @Query("page") page: String): Call<ProductItem>

    @POST("/api/users/login")
    fun login(
        @Body jsonparams: RequestBody
    ): Call<Void>

    @POST("/api/users")
    fun signUp(
        @Body jsonparams: RequestBody
    ): Call<Void>

    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun search(
        //@Header("Authorization") accessToken: String,
        @Query("searchWord") searchTerm: String): Call<ProductItem>
}

object InomApi {
    val SharedPreferences = App.instance.getSharedPreferences("access", 0)
    // private const val baseUrl = "https://inu-market.cf/"  // 베이스 URL
    private const val baseUrl = "http://13.209.183.154:8080"

    init {
        //로깅인터셉터 설정 추가하였습니다
        val interceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(TAG, "InomApi - log() called / message: $message")
                when {
                    message.isJsonArray() ->
                        Log.d(TAG, JSONArray(message).toString(4))
                    message.isJsonObject() ->
                        Log.d(TAG, JSONObject(message).toString(4))//json형태이거나 배열이면 로그를 찍을 때 들여쓰기를 해줍니다!
                    else->{
                        try {
                            Log.d(TAG, JSONObject(message).toString(4))
                        }catch (e:Exception){
                            Log.d(TAG,message)
                        }
                    }
                }
            }

        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        //액세스토큰을 기본적으로 넣어주는 인터셉터
        val baseParameterInterceptor : Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                Log.d(TAG, "RetrofitClient - intercept() called")
                // 오리지날 리퀘스트
                val originalRequest = chain.request()

                // 액세스토큰 파라매터 추가하기
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", SharedPreferences.getString("accessToken","")).build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }
        })

        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(baseParameterInterceptor)
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()

        //Retrofit : type-safe한 HTTP 클라이언트 라이브러리
        //retrofit은 Okhttp 클라이언트를 디폴트로 선언
        //다른 HTTP 모듈보다 빠름
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder)
            .build()
    }


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