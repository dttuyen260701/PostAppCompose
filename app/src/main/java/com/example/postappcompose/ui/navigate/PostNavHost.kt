package com.example.postappcompose.ui.navigate

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.postappcompose.ui.launch.LaunchViewModel
import com.example.postappcompose.ui.launch.SignInScreen
import com.example.postappcompose.ui.launch.SignUpScreen


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

                },
                onMoveToSignIn = {
                    navController.navigateUp()
                },
                launchViewModel = launchViewModel
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
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
    }
