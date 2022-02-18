package com.example.inomtest.dataClass

import java.io.Serializable

data class ProductResult (var thumbnail : String?,
                            var title : String?,
                            var price : Int?,
                            var likes : Int?):Serializable{
}