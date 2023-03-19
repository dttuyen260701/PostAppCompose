package com.example.postappcompose.data.dao

import androidx.room.*
import com.example.postappcompose.data.models.User
import com.example.postappcompose.data.models.UserWithFavorite

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User WHERE iD_User = :id")
    fun getUserByID(id: Int): UserWithFavorite

    @Query("SELECT COUNT(*) FROM User WHERE email = :email")
    fun getCountUserMail(email: String): Int

    @Query("SELECT * FROM User WHERE email = :email and password = :pass")
    fun loginUser(email: String, pass: String): UserWithFavorite

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(vararg users: User)

    @Update
    fun updateUser(vararg user: User)
}
