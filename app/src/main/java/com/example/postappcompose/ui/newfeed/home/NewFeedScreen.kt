package com.example.postappcompose.ui.newfeed.home

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.postappcompose.R
import com.example.postappcompose.ui.component.ToolbarView
import com.example.postappcompose.ui.theme.PostAppTheme

@Composable
fun NewFeedScreen(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
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

        }
        FloatingActionButton(
            onClick = {

            },
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
}

@Composable
@Preview
fun PreviewLoginScreen() {
    NewFeedScreen {

    }
}

