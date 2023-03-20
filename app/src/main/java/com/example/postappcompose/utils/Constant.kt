package com.example.postappcompose.utils

import com.example.postappcompose.data.models.User
import com.example.postappcompose.data.models.UserWithFavorite

object Constant {
    var userWithFavorite: UserWithFavorite = UserWithFavorite(
        user = User(iD_User = -1, name = "", email = "", password = ""),
        ArrayList()
    )
}
