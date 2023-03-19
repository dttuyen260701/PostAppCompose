package com.example.postappcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val iD_Post: Int = 0,
    @ColumnInfo val iD_User_Create: Int = 0,
    @ColumnInfo val content: String = "",
    @ColumnInfo val time_Create: String = ""
)
