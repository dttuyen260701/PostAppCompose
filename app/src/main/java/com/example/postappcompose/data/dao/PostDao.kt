package com.example.postappcompose.data.dao

import androidx.room.*
import com.example.postappcompose.data.models.Post
import com.test.test_by_anh_phu.bai1.data.bai1.models.PostWithFavorite

@Dao
interface PostDao {
    @Transaction
    @Query("SELECT * FROM Post ORDER BY iD_Post DESC LIMIT :page, :step")
    fun getPost(page: Int, step: Int): List<PostWithFavorite>

    @Transaction
    @Query("SELECT * FROM Post WHERE iD_User_Create = :id_user ORDER BY iD_Post DESC LIMIT :page, :step")
    fun getPostByIDUser(id_user: Int, page: Int, step: Int): List<PostWithFavorite>

    @Transaction
    @Query("SELECT * FROM Post INNER JOIN FavoritePost ON Post.iD_Post = FavoritePost.iD_Post WHERE FavoritePost.iD_User = :id_user ORDER BY iD_Post DESC LIMIT :page, :step")
    fun getPostFavoriteByIDUser(
        id_user: Int,
        page: Int,
        step: Int
    ): List<PostWithFavorite>

    @Transaction
    @Query("SELECT * FROM Post WHERE iD_Post = :id_post")
    fun getPostByID(id_post: Int): List<PostWithFavorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(vararg posts: Post)

    @Update
    fun updatePost(vararg post: Post)

    @Delete
    fun deletePost(vararg post: Post)
}
