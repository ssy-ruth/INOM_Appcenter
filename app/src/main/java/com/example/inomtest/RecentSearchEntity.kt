package com.example.inomtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentSearchWord")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true)//지금 id가 PrimaryKey인데 id를 지정해주지 않아도 알아서 숫자가 지정된다는 뜻!
    var id:Long?,
    var recentWord:String="")