package com.example.inomtest.dataClass

import android.app.Notification
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class NotificationDTO(val data: DataNotification)

data class DataNotification(val items: ArrayList<NotificationData>)

data class NotificationData(
    @SerializedName("notificationId")
    val notificationId : Int = 1,

    @SerializedName("content")
    val content : String  = "내용",

    @SerializedName("read")
    val read : Boolean = false,

    @SerializedName("notificationType")
    val notificationType : String = "TRADE",

    @SerializedName("referenceId")
    val referenceId : Int = 1,

    @SerializedName("createAt")
    val createdAt : String = "2022-01-20T21:28:50"
)