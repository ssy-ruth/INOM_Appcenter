package com.example.inomtest

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inomtest.fragment.SearchFragment


@Database(entities = arrayOf(RecentSearchEntity::class), version = 1)//Entity파일이든, 데이터베이스에 수정이 있을 때 버전을 올려주어야 해요!
abstract class RecentSearchDatabase : RoomDatabase() {
    //DAO를 반환하는 추상화메소드
    abstract fun recentSearchDAO() : RecentSearchDAO

    companion object {
        var INSTANCE : RecentSearchDatabase? = null

        fun getInstance(context: SearchFragment) : RecentSearchDatabase?{
            if (INSTANCE == null){
                synchronized(RecentSearchDatabase::class){
                    INSTANCE= Room.databaseBuilder(context.applicationContext,
                    RecentSearchDatabase::class.java, "recentWord.db")
                        .fallbackToDestructiveMigration()//DB 버전이 업그레이드 되면 기존 데이타를 다 삭제하고 새로 table을 구성한다는 의미!
                        .build()
                }
            }
            return INSTANCE
        }
    }

}