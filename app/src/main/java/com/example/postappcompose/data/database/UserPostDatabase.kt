package com.example.postappcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postappcompose.data.dao.FavoritePostDao
import com.example.postappcompose.data.dao.PostDao
import com.example.postappcompose.data.dao.UserDao
import com.example.postappcompose.data.models.FavoritePost
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.User

@Database(entities = [User::class, Post::class, FavoritePost::class], version = 1)
abstract class UserPostDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun favoriteDao(): FavoritePostDao
}
