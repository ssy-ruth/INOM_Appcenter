package com.example.inomtest.dataClass

import android.graphics.drawable.Drawable
import com.example.inomtest.databinding.ItemLoadingBinding
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList


data class ItemData(
    @SerializedName("itemId")
    var itemId: Int = 0,

    @SerializedName("title")
    val title: String = "제목 초기화",

    @SerializedName("contents")
    val contents: String = "내용 초기화",

    @SerializedName("price")
    val price: Int = 0,

    @SerializedName("favoriteCount")
    val favoriteCount : Int = 0,

    @SerializedName("status")
    val status : String = "상태 초기화",

    @SerializedName("favorite")
    val favorite : Boolean = false,

    @SerializedName("createdAt")
    val createdAt : Date?,

    @SerializedName("updatedAt")
    val updatedAt : Date?,

    @SerializedName("major")
    val major : Major?,

    @SerializedName("category")
    val category : Category?,

    @SerializedName("seller")
    val seller : Seller?,

    @SerializedName("imageUrls")
    val imageUrls: String = " "
)

data class Major (
    @SerializedName("majorID")
    val majorID: String,

    @SerializedName("name")
    val name: String
)

data class Category (
    @SerializedName("categoryID")
    val categoryID: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("iconUrl")
    val iconUrl: String
)

data class Seller (
    @SerializedName("userID")
    val userID: Int,

    @SerializedName("nickName")
    val nickName: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("score")
    val score: Float,

    @SerializedName("pushToken")
    val pushToken: String
)
