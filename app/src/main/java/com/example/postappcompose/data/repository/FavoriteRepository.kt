package com.example.postappcompose.data.repository

import androidx.annotation.WorkerThread
import com.example.postappcompose.data.dao.FavoritePostDao
import com.example.postappcompose.data.models.FavoritePost
import javax.inject.Inject

/*
 * Created by tuyen.dang on 10/9/2022
 */

class FavoriteRepository @Inject constructor(private val favoritePostDao: FavoritePostDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFavorite(favoritePost: FavoritePost) {
        favoritePostDao.insertFavorite(favoritePost)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteFavorite(favoritePost: FavoritePost) {
        favoritePostDao.deleteFavorite(favoritePost)
    }
}
