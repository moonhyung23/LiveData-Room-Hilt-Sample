package com.example.a220410_rvdatabinding_clone.di

import android.content.Context
import com.example.a220410_rvdatabinding_clone.UserDao
import com.example.a220410_rvdatabinding_clone.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Database관련 모듈이기 때문에 singletonComponent 사용
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getInstance(context)
    }

    //Room의 식물 정보에 접근할 수 있는 객체 제공
    @Provides
    fun provideUserDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }
}