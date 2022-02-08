package com.example.inomtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.inomtest.dataClass.Data

class MainViewModel : ViewModel() {
    private val productRepository = ProductRepository()
    private val items: LiveData<Data>
        get() = productRepository._products

    fun loadProductItems(accessToken: String, page: Int) {
        productRepository.loadProductItems(accessToken, page)
    }

    fun getAll(): LiveData<Data> {
        return items
    }
}