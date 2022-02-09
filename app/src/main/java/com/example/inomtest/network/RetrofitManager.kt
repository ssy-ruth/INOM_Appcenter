package com.example.inomtest.network

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import com.example.inomtest.dataClass.ProductItem
import com.example.inomtest.fragment.SearchFragment
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }
    //레트로핏 인터페이스 가져오기
    private val iRetrofit :InomApiService = InomApi.createApi()

    //검색 api 호출
    fun searchWord(searchTerm:String?, completion:(RESPONSE_STATE, String)->Unit){

        val term = searchTerm.let {
            it
        }?:""

        val callSearch = iRetrofit.search(searchTerm = term).let{
            it
        }?: return

        callSearch.enqueue(object: retrofit2.Callback<ProductItem>{
            //응답성공시
            override fun onResponse(call: Call<ProductItem>, response: Response<ProductItem>) {
                Log.d(TAG, "RetrofitMasager - onResponce() called / t ${response.raw()}")
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

//익스텐션 함수 : 문자열이 json 형태나 배열인지 확인
fun String.isJsonObject(): Boolean {
    return this?.startsWith("{") && this.endsWith("}")

}
 fun String.isJsonArray(): Boolean {
     return this?.startsWith("[") && this.endsWith("]")
}
