package com.example.inomtest.network

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import com.example.inomtest.dataClass.ProductItem
import com.example.inomtest.fragment.SearchFragment
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    //검색 api 호출
    fun searchWord(completion:(RESPONSE_STATE, String)->Unit){
        //레트로핏 인터페이스 가져오기
        val iRetrofit :InomApiService = InomApi.createApi()

        //검색어, 액세스 토큰 가져오기
        val SharedPreferences = App.instance.getSharedPreferences("access", Context.MODE_PRIVATE)
        var access = SharedPreferences.getString("accessToken", "")
        val gson = Gson()
        var searchTerm = SharedPreferences.getString("searchWord","")
        var term = searchTerm.let {
            it
        }?:""
        var term1 = gson.toJson(term)
        val callSearch = iRetrofit.search(Authorization = access.toString(), searchTerm = term, size = 10).let{
            it
        }?: return

        callSearch.enqueue(object: retrofit2.Callback<ProductItem>{
            //응답성공시
            override fun onResponse(call: Call<ProductItem>, response: Response<ProductItem>) {
                Log.d(TAG, "RetrofitMasager - onResponce() called / t ${response.raw()}")
                Log.d(TAG, "검색어 : $term, 토큰 : $access")
                completion(RESPONSE_STATE.OKAY, response.raw().toString())
            }

            //응답실패시
            override fun onFailure(call: Call<ProductItem>, t: Throwable) {
                Log.d(TAG, "RetrofitMasager - onResponce() called / t $t")
                completion(RESPONSE_STATE.FAIL, t.toString())
            }

        })
    }
    enum class RESPONSE_STATE{
        OKAY,
        FAIL
    }
}

//익스텐션 함수 : 문자열이 json 형태나 배열인지 확인 _ InomApiService 헤더 자동으로 추가해주는 부분에서 사용
fun String.isJsonObject(): Boolean {
    return this?.startsWith("{") && this.endsWith("}")

}
 fun String.isJsonArray(): Boolean {
     return this?.startsWith("[") && this.endsWith("]")
}
