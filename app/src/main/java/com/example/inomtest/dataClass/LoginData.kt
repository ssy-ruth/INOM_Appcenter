package com.example.inomtest.dataClass

import com.google.gson.annotations.SerializedName

data class LoginData (
    @SerializedName("inuId") var inuId: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("pushToken") var pushToken: String? = null
)