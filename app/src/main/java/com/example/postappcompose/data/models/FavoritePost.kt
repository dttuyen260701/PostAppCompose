package com.example.postappcompose.data.models

import androidx.room.Entity

/*
 * Created by tuyenpc on 10/9/2022
 */
@Entity(primaryKeys = ["iD_Post", "iD_User"])
data class FavoritePost(
    val iD_Post: Int = 0,
    val iD_User: Int = 0
)
