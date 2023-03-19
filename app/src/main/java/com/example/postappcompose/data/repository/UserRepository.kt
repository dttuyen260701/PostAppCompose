package com.example.postappcompose.data.repository

import androidx.annotation.WorkerThread
import com.example.postappcompose.data.dao.UserDao
import com.example.postappcompose.data.models.User
import com.example.postappcompose.data.models.UserWithFavorite
import javax.inject.Inject

/*
 * Created by tuyenpc on 10/9/2022
 */

class UserRepository @Inject constructor(private val userDao: UserDao) {
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun getUserByID(id: Int): UserWithFavorite = userDao.getUserByID(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUser(vararg user: User) {
        userDao.insertUser(*user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCountUserMail(email: String): Int = userDao.getCountUserMail(email)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun loginUser(email: String, pass: String): UserWithFavorite = userDao.loginUser(email, pass)
}
