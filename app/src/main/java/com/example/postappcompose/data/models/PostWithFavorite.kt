package com.test.test_by_anh_phu.bai1.data.bai1.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.postappcompose.data.models.FavoritePost
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.User

/*
 * Created by tuyenpc on 10/9/2022
 */

data class PostWithFavorite(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "iD_User_Create",
        entityColumn = "iD_User"
    )
    val user: User,
    @Relation(
        parentColumn = "iD_Post",
        entityColumn = "iD_User",
        associateBy = Junction(FavoritePost::class)
    )
    var listUser: List<User>
)
