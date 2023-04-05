package com.example.postappcompose.ui.newfeed.home

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.postappcompose.R
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.PostWithFavorite
import com.example.postappcompose.data.models.User
import com.example.postappcompose.ui.component.ToolbarView
import com.example.postappcompose.ui.newfeed.PostViewModel
import com.example.postappcompose.ui.newfeed.items.PostItem
import com.example.postappcompose.ui.theme.PostAppTheme
import com.example.postappcompose.utils.Constant
import kotlinx.coroutines.launch

@Composable
fun NewFeedScreen(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
    openAddScreen: () -> Unit,
    postViewModel: PostViewModel
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val listData = remember {
        mutableListOf<PostWithFavorite>().toMutableStateList()
    }
    postViewModel.getPostData().observe(lifecycleOwner) {
        listData.clear()
        listData.addAll(it)
        coroutineScope.launch {
//            listState.animateScrollToItem(index = listData.lastIndex)
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .then(modifier)
    ) {
        val (toolbar, lzlPost, btnAdd) = createRefs()
        ToolbarView(
            title = stringResource(id = R.string.NewFeedTweet),
            leftIcon = R.drawable.ic_logout,
            onLeftIconClick = {
                onSignOut()
                Toast.makeText(
                    context,
                    R.string.SignOutSuccess,
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                }
        )
        LazyColumn(
            modifier = Modifier
                .constrainAs(lzlPost) {
                    top.linkTo(toolbar.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize(),
            state = listState
        ) {
            items(
                listData,
                key = { postWF -> postWF.post.iD_Post }
            ) { post ->
                PostItem(item = post)
            }
        }
        FloatingActionButton(
            onClick = openAddScreen,
            modifier = Modifier
                .size(56.dp)
                .constrainAs(btnAdd) {
                    bottom.linkTo(parent.bottom, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                },
            backgroundColor = PostAppTheme.colors.primaryVariant
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }

    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                postViewModel.getPost(
                    isReload = true,
                    id = Constant.userWithFavorite.user.iD_User,
                    onStart = {

                    },
                    onResult = {

                    }
                )
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
@Preview
fun PreviewLoginScreen() {
    NewFeedScreen (onSignOut = {}, openAddScreen = {}, postViewModel = hiltViewModel())
}

