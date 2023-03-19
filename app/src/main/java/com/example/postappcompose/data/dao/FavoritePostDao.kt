package com.example.postappcompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.postappcompose.data.models.FavoritePost

/*
 * Created by tuyen.dang on 10/9/2022
 */

@Dao
interface FavoritePostDao {
    @Insert
    fun insertFavorite(vararg favorites: FavoritePost)

    @Delete
    fun deleteFavorite(vararg favorites: FavoritePost)
}
