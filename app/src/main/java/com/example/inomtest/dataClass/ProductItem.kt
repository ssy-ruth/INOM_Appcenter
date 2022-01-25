package com.example.inomtest.dataClass

import android.graphics.drawable.Drawable

data class ProductItem(val data: Data)

data class Data(val items: ArrayList<ItemData>)

data class ItemData(
    val img: Drawable?,
    val item_title: String,
    val item_price: String
)
