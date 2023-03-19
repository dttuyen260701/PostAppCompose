package com.test.test_by_anh_phu.bai1.utils

import com.test.test_by_anh_phu.bai1.data.bai1.models.User
import com.test.test_by_anh_phu.bai1.data.bai1.models.UserWithFavorite

object Constant {
    var userWithFavorite: UserWithFavorite = UserWithFavorite(
        user = User(iD_User = -1, name = "", email = "", password = ""),
        ArrayList()
    )
}
