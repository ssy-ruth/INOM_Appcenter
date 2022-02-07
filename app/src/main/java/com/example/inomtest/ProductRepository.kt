package com.example.inomtest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.inomtest.dataClass.Data
import com.example.inomtest.dataClass.ProductItem
import com.example.inomtest.network.InomApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductRepository {
    // private val parameter: MutableMap<String, String> = HashMap()
    var _products = MutableLiveData<Data>()

    fun loadProductItems(page: Int) {
        // parameter["page"] = page.toString()
        val call = InomApi.createApi().loadProducts("Bearer **", page.toString())

        call.enqueue(object : Callback<ProductItem> {
            override fun onResponse(
                call: Call<ProductItem>,
                response: Response<ProductItem>
            ) {
                if (response.isSuccessful) {
                    Log.d("결과1", "통신결과"+response.code().toString())
                    _products.value = response.body()!!.data
                }

                else {
                    Log.d("결과2", "통신결과"+response.code().toString())
                }
            }

            override fun onFailure(call: Call<ProductItem>, t: Throwable) {
                Log.d("결과3", "통신결과: $t")
            }
        })
    }
}