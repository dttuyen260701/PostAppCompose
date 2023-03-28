package com.example.postappcompose.ui.navigate

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.postappcompose.ui.launch.LaunchViewModel
import com.example.postappcompose.ui.launch.SignInScreen
import com.example.postappcompose.ui.launch.SignUpScreen
import com.example.postappcompose.ui.newfeed.addpost.AddPostScreen
import com.example.postappcompose.ui.newfeed.home.NewFeedScreen


@Composable
fun PostNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SignInView.route,
        modifier = modifier
    ) {
        composable(SignInView.route) {
            val signInEntry = remember(it) {
                navController.getBackStackEntry(SignInView.route)
            }
            val launchViewModel = hiltViewModel<LaunchViewModel>(
                signInEntry
            )
            SignInScreen(
                onSignInSuccess = {
                    navController.navigate(NewFeedRoute.route) {
                        popUpTo(SignInView.route) { inclusive = true }
                    }
                },
                onMoveToSignUp = {
                    navController.navigate(SignUpView.route)
                },
                launchViewModel = launchViewModel
            )
        }

        composable(SignUpView.route) {
            BackHandler {
                navController.navigateUp()
            }

            val signUpEntry = remember(it) {
                navController.getBackStackEntry(SignUpView.route)
            }
            val launchViewModel = hiltViewModel<LaunchViewModel>(
                signUpEntry
            )

            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigateUp()
                },
                onMoveToSignIn = {
                    navController.navigateUp()
                },
                launchViewModel = launchViewModel
            )
        }

        navigation(
            startDestination = (PostTabs.TWEET.route),
            route = NewFeedRoute.route
        ) {
            composable(PostTabs.TWEET.route) {
                NewFeedScreen(
                    Modifier.background(Color.Red)
                )
            }

            composable(PostTabs.FAVORITE.route) {
                NewFeedScreen(
                    Modifier.background(Color.Green)
                )
            }

            composable(AddPost.route) {
                AddPostScreen()
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(
    route: String,
    beforeNavigated: () -> Unit = {}
) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
        beforeNavigated.invoke()
    }
