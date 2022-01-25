package com.example.inomtest

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
        val call = InomApi.createApi().loadProducts(page.toString())

        call.enqueue(object : Callback<ProductItem> {
            override fun onResponse(
                call: Call<ProductItem>,
                response: Response<ProductItem>
            ) {
                if (response.isSuccessful) {
                    _products.value = response.body()!!.data
                }
            }

            override fun onFailure(call: Call<ProductItem>, t: Throwable) {

            }
        })
    }
}