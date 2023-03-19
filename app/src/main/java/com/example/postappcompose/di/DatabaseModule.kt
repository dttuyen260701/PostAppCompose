package com.example.postappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.postappcompose.data.dao.FavoritePostDao
import com.example.postappcompose.data.dao.PostDao
import com.example.postappcompose.data.dao.UserDao
import com.example.postappcompose.data.database.UserPostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): UserPostDatabase {
        return Room.databaseBuilder(appContext, UserPostDatabase::class.java, "nameDatabase").build()
    }

    @Provides
    fun providePostDao(appDB: UserPostDatabase): PostDao {
        return appDB.postDao()
    }

    @Provides
    fun provideFavoriteDao(appDB: UserPostDatabase): FavoritePostDao {
        return appDB.favoriteDao()
    }

    @Provides
    fun provideUserDao(appDB: UserPostDatabase): UserDao {
        return appDB.userDao()
    }
}