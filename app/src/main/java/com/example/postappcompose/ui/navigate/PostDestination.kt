package com.example.postappcompose.ui.navigate

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