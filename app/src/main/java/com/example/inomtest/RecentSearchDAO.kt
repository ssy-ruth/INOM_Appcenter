package com.example.inomtest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RecentSearchDAO {
    @Insert(onConflict = REPLACE)//PrimaryKey가 똑같으면 나중에 생성한 것으로 덮어씌운다는 의미
    fun insert(recentWord : RecentSearchEntity)

    @Query("SELECT * FROM RecentSearchWord")//DB에서 저장된 데이타들을 가져옴
    fun getAll() : List<RecentSearchEntity>

    @Delete
    fun delete(recentWord: RecentSearchEntity)
}