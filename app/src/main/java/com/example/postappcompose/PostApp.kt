package com.example.postappcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.postappcompose.ui.navigate.PostNavHost
import com.example.postappcompose.ui.navigate.PostTabs
import com.example.postappcompose.ui.navigate.navigateSingleTopTo
import com.example.postappcompose.ui.theme.PostAppTheme

@Composable
fun PostApp(
    navController : NavHostController
) {
    val tabs = remember { PostTabs.values() }
    Scaffold(
        backgroundColor = Color.Transparent,
        bottomBar = { PostBottomBar(navController = navController, tabs) }
    ) { innerPaddingModifier ->
        PostNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}

@Composable
fun PostBottomBar(
    navController : NavHostController,
    tabs: Array<PostTabs>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: PostTabs.TWEET.route

    val routes = remember { PostTabs.values().map { it.route } }
    if (currentRoute in routes) {
        BottomNavigation(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            )
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    label = { Text(stringResource(tab.title)) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.run {
                                navigateSingleTopTo(tab.route) {
                                    popBackStack()
                                }
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = PostAppTheme.colors.primary,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier.navigationBarsPadding().background(Color.White)
                )
            }
        }
    }
}