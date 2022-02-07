package com.example.inomtest.dataClass

import android.graphics.drawable.Drawable
import java.util.*
import kotlin.collections.ArrayList

data class ProductItem(val data: Data)

data class Data(val items: ArrayList<ItemData>)

data class ItemData(
    val itemId: Int = 0,
    val title: String = "제목 초기화",
    val contents: String = "내용 초기화",
    val price: Int = 0,
    val favoriteCount : Int = 0,
    val status : String = "상태 초기화",
    val favorite : Boolean = false,
    val createdAt : Date?,
    val updatedAt : Date?,
    val major : Major?,
    val category : Category?,
    val seller : Seller?,
    val imageUrls: String = " "
)

data class Major (
    val majorID: String,
    val name: String
)

data class Category (
    val categoryID: Int,
    val name: String,
    val iconUrl: String
)

data class Seller (
    val userID: Int,
    val nickName: String,
    val imageUrl: String,
    val score: Float,
    val pushToken: String
)
