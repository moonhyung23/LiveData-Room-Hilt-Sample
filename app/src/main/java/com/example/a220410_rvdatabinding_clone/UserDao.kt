package com.example.a220410_rvdatabinding_clone

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Insert
    fun addUserDb(users: List<User>)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    //처음 DB에 저장된 id를 갖고옴
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): Flow<List<User>>

    //
    @Query("SELECT * FROM user_table WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<User>>
}