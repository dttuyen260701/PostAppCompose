package com.example.postappcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val iD_User: Int = 0,
    @ColumnInfo val email: String = "",
    @ColumnInfo val password: String = "",
    @ColumnInfo val name: String = "Name",
    @ColumnInfo val link_Image: String = "https://i.rada.vn/data/image/2019/07/29/hinh-nen-manchester-united-200.png"
)
