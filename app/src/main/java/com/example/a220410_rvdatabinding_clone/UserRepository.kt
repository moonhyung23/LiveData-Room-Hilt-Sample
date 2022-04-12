package com.example.a220410_rvdatabinding_clone

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    //DB에 저장된 데이터를 Flow를 통해서 갖고오기.
    //LiveData는 동기적으로 작동하기 때문에 비동기에 맞지 않음
    val readAllData: Flow<List<User>> = userDao.readAllData()

    //추가
    fun addUser(user: User) {
        userDao.addUser(user)
    }

    //수정
    fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    //삭제
    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    //검색
    fun searchDatabase(searchQuery: String): Flow<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }

}