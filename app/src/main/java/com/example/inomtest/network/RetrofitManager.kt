package com.example.inomtest.network

import com.example.inomtest.dataClass.ProductItem
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager
    }
    //레트로핏 인터페이스 가져오기
    private val iRetrofit :InomApiService = InomApi.createApi()

    //검색 api 호출
    fun searchWord(searchTerm:String?, completion:(String)->Unit){

        val term = searchTerm.let {
            it
        }?:""

        val callSearch = iRetrofit.search(searchTerm = term).let{
            it
        }?: return

        callSearch.enqueue(object: retrofit2.Callback<ProductItem>{
            //응답성공시
            override fun onResponse(call: Call<ProductItem>, response: Response<ProductItem>) {
                completion(response.raw().toString())
            }

            //응답실패시
            override fun onFailure(call: Call<ProductItem>, t: Throwable) {

            }

        })
    }
}