package com.example.inomtest.network

import android.content.ContentValues.TAG
import android.util.Log
import com.example.inomtest.dataClass.ProductItem
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface InomApiService {
    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun loadProducts(@Query("page") page: String): Call<ProductItem>

    @GET("/api/items?size=10&itemId=1000&categoryId=10&majorId=10&searchWord=10")
    fun search(@Query("searchWord") searchTerm: String): Call<ProductItem>
}

object InomApi {
    private const val baseUrl = "https://inu-market.cf/"  // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d(TAG, "RetrofitClient - getClient() called")

        // okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {

            override fun log(message: String) {
//                Log.d(TAG, "RetrofitClient - log() called / message: $message")

                when {
                    message.isJsonObject() ->
                        Log.d(TAG, JSONObject(message).toString(4))
                    message.isJsonArray() ->
                        Log.d(TAG, JSONObject(message).toString(4))
                    else -> {
                        try {
                            Log.d(TAG, JSONObject(message).toString(4))
                        } catch (e: Exception) {
                            Log.d(TAG, message)
                        }
                    }
                }

            }

        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)


        // 기본 파라미터 인터셉터 설정
        val baseParameterInterceptor: Interceptor = (object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")
                // 오리지날 리퀘스트
                val originalRequest = chain.request()

                // ?client_id=asdfadsf
                // 쿼리 파라미터 추가하기
                val addedUrl =
                    originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID)
                        .build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }

        })


        // 위에서 설정한 기본파라매터 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(baseParameterInterceptor)

        // 커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        fun createApi(): InomApiService {
            return retrofit.create(
                InomApiService::class.java
            )
        }

        // 문자열이 제이슨 형태인지
        fun String?.isJsonObject(): Boolean {
            if (this?.startsWith("{") == true && this.endsWith("}")) {
                return true
            } else {
                return false
            }
//    return this?.startsWith("{") == true && this.endsWith("}")
        }
//fun String?.isJsonObject():Boolean = this?.startsWith("{") == true && this.endsWith("}")

        // 문자열이 제이슨 배열인지
        fun String?.isJsonArray(): Boolean {
            if (this?.startsWith("[") == true && this.endsWith("]")) {
                return true
            } else {
                return false
            }
        }
    }

