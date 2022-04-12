package com.example.a220410_rvdatabinding_clone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
//RoomDataBase 상속
abstract class UserDatabase : RoomDatabase() {
    //데이터베이스에 접근할 수 있는 추상 객체
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null


        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(UserDatabase::class) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //싱글턴으로 생성해서 어디서든 접근할 수 있도록 한다.
        //DataBase 객체 반환
        fun buildDatabase(context: Context): UserDatabase {
            //객체가 없는 경우 객체를 생성
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            ).build()
        }
    }
}
