package com.example.postappcompose.ui.newfeed.addpost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.postappcompose.R
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.extension.getDay
import com.example.postappcompose.ui.component.BorderLine
import com.example.postappcompose.ui.component.SingleButton
import com.example.postappcompose.ui.component.ToolbarView
import com.example.postappcompose.ui.newfeed.PostViewModel
import com.example.postappcompose.ui.textstyle.TextStyleApp
import com.example.postappcompose.ui.theme.PostAppTheme
import com.example.postappcompose.utils.Constant

@Composable
fun AddPostScreen(
    modifier: Modifier = Modifier,
    backToNewFeed: () -> Unit,
    postViewModel: PostViewModel
) {
    val content = remember {
        postViewModel.content
    }
    ConstraintLayout(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .then(modifier)
    ) {
        val (toolbar, borderHeader, tvPost, btnAdd, borderEnd) = createRefs()
        ToolbarView(
            title = stringResource(id = R.string.AddPostTitle),
            leftIcon = R.drawable.ic_back,
            onLeftIconClick = backToNewFeed,
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                }
        )

        BorderLine(
            modifier = Modifier
                .height(0.8.dp)
                .constrainAs(borderHeader) {
                    top.linkTo(toolbar.bottom)
                }
        )

        TextField(
            value = content.value,
            onValueChange = {
                content.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.AddPostYourFeeling),
                    color = Color.White.copy(alpha = 0.5f),
                    style = TextStyleApp.textColorWhite(textAlign = TextAlign.Start),
                )
            },
            textStyle = TextStyleApp.textColorWhite(textAlign = TextAlign.Start),
            modifier = Modifier
                .constrainAs(tvPost) {
                    top.linkTo(borderHeader.bottom, 50.dp)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, 20.dp)
                    bottom.linkTo(borderEnd.top, 50.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color.Transparent),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = PostAppTheme.colors.primary,
                cursorColor = Color.White,
                leadingIconColor = Color.Red,
                trailingIconColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorCursorColor = Color.Red,
                disabledIndicatorColor = Color.Transparent
            ),
        )

        BorderLine(
            modifier = Modifier
                .height(0.33.dp)
                .constrainAs(borderEnd) {
                    bottom.linkTo(btnAdd.top, 20.dp)
                }
        )

        SingleButton(
            onClick = {
                postViewModel.insertPost(
                    post = arrayOf(
                        Post(
                            iD_User_Create = Constant.userWithFavorite.user.iD_User,
                            content = content.value,
                            time_Create = getDay()
                        )
                    ), onStart = {

                    }, onResult = backToNewFeed
                )
            },
            modifier = Modifier
                .height(54.dp)
                .fillMaxWidth()
                .constrainAs(btnAdd) {
                    bottom.linkTo(parent.bottom, 40.dp)
                }
                .padding(horizontal = 20.dp),
            textBtn = stringResource(id = R.string.AddPostBtnAdd),
            backgroundColor = PostAppTheme.colors.backgroundButton
        )
    }
}

@Composable
@Preview
fun PreviewAddScreen() {
    AddPostScreen(postViewModel = hiltViewModel(), backToNewFeed = {})
}