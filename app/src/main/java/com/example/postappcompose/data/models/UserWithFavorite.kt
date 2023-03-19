package com.example.postappcompose.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.postappcompose.data.models.FavoritePost
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.User

/*
 * Created by tuyen.dang on 10/9/2022
 */

data class UserWithFavorite(
    @Embedded val user: User,
    @Relation(
        parentColumn = "iD_User",
        entityColumn = "iD_Post",
        associateBy = Junction(FavoritePost::class)
    )
    val listPost: List<Post>
)
