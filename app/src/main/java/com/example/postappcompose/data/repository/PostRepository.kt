package com.example.postappcompose.data.repository

import androidx.annotation.WorkerThread
import com.example.postappcompose.data.dao.PostDao
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.PostWithFavorite
import javax.inject.Inject

/*
 * Created by tuyenpc on 10/9/2022
 */

class PostRepository @Inject constructor(private val postDao: PostDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPost(vararg post: Post) {
        postDao.insertPosts(*post)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getPostByIDUser(id_U: Int, page: Int, step: Int): List<PostWithFavorite> {
        return if (id_U == 0) postDao.getPost(page, step) else postDao.getPostByIDUser(
            id_U,
            page,
            step
        )
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getPostByIDPost(id_Post: Int): List<PostWithFavorite> {
        return postDao.getPostByID(id_Post)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getPostFavoriteByIDUser(
        id_U: Int,
        page: Int,
        step: Int
    ): List<PostWithFavorite> {
        return postDao.getPostFavoriteByIDUser(
            id_U,
            page,
            step
        )
    }
}
