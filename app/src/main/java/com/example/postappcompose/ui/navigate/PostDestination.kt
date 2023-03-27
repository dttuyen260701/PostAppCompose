package com.example.postappcompose.ui.navigate

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.postappcompose.R

interface PostDestination {
//    val icon: ImageVector
    val route: String
}

object SignInView : PostDestination {
//    override val icon = Icons.Filled.
    override val route = "signIn"
}

object SignUpView : PostDestination {
    //    override val icon = Icons.Filled.
    override val route = "signUp"
}

object NewFeedRoute : PostDestination {
    override val route = "newFeed"
}

object AddPost : PostDestination {
    override val route = "add"
}


enum class PostTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    TWEET(R.string.NaviHome, R.drawable.ic_home, PostDestinations.TWEET_ROUTE),
    FAVORITE(R.string.NaviFavorite, R.drawable.ic_favorite, PostDestinations.FAVORITE_ROUTE)
}
private object PostDestinations {
    const val TWEET_ROUTE = "post/tweet"
    const val FAVORITE_ROUTE = "post/favorite"
}