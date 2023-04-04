package com.example.postappcompose.ui.newfeed.addpost

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.postappcompose.R
import com.example.postappcompose.ui.component.ToolbarView

@Composable
fun AddPostScreen(
    modifier: Modifier = Modifier,
    backToNewFeed: () -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    ConstraintLayout(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .then(modifier)
    ) {
        val (toolbar, borderHeader, tvPost, btnAdd) = createRefs()
        ToolbarView(
            title = stringResource(id = R.string.AddPostTitle),
            leftIcon = R.drawable.ic_back,
            onLeftIconClick = backToNewFeed,
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                }
        )
    }
}

@Composable
@Preview
fun PreviewAddScreen() {
    AddPostScreen() {

    }
}