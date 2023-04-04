package com.example.postappcompose.ui.newfeed.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.postappcompose.R
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.User
import com.example.postappcompose.ui.component.BorderLine
import com.example.postappcompose.ui.textstyle.TextStyleApp
import com.example.postappcompose.ui.theme.PostAppTheme
import com.example.postappcompose.data.models.PostWithFavorite

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    item: PostWithFavorite
) {
    ConstraintLayout(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        var isShown by remember {
            mutableStateOf(true)
        }
        val (
            imgUser,
            tvNameUser,
            tvAccount,
            btnShowHide,
            tvContentPost,
            tvTime,
            tvLinkTweet,
            borderPost,
            btnFavorite,
            tvTotalLike,
            borderEnd
        ) = createRefs()

        AsyncImage(
            model = item.user.link_Image,
            contentDescription = null,
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .constrainAs(imgUser) {
                    top.linkTo(parent.top, 42.dp)
                    start.linkTo(parent.start, 20.dp)
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.user.name,
            style = TextStyleApp.textColorBlack(16, TextAlign.Start),
            modifier = Modifier
                .constrainAs(tvNameUser) {
                    top.linkTo(imgUser.top, 5.dp)
                    linkTo(imgUser.end, btnShowHide.start, startMargin = 10.dp, endMargin = 10.dp, bias = 0F)
                    width = Dimension.preferredWrapContent
                    height = Dimension.preferredWrapContent
                }
        )

        Text(
            text = "@${item.user.name}",
            style = TextStyleApp.textColorGray(14, TextAlign.Start),
            modifier = Modifier
                .constrainAs(tvAccount) {
                    top.linkTo(tvNameUser.bottom)
                    linkTo(imgUser.end, btnShowHide.start, startMargin = 10.dp, endMargin = 10.dp, bias = 0F)
                    width = Dimension.preferredWrapContent
                    height = Dimension.preferredWrapContent
                }
        )

        Icon(
            painter = painterResource(id = if(isShown) R.drawable.ic_up else R.drawable.ic_down),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .constrainAs(btnShowHide) {
                    top.linkTo(imgUser.top)
                    bottom.linkTo(imgUser.bottom)
                    end.linkTo(parent.end, 20.dp)
                }
                .clickable {
                    isShown = !isShown
                }
        )

        Text(
            text = item.post.content,
            style = TextStyleApp.textColorBlack(13, TextAlign.Start),
            modifier = Modifier
                .constrainAs(tvContentPost) {
                    top.linkTo(imgUser.bottom, 25.dp)
                }
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Text(
            text = item.post.time_Create,
            style = TextStyleApp.textColorBlack(16, TextAlign.Start),
            modifier = Modifier
                .constrainAs(tvTime) {
                    top.linkTo(tvContentPost.bottom, 40.dp)
                    start.linkTo(parent.start, 20.dp)
                    width = Dimension.preferredWrapContent
                }
        )

        Text(
            text = stringResource(R.string.twitter),
            style = TextStyleApp.textColorBlack(16, TextAlign.Start),
            color = PostAppTheme.colors.primaryVariant,
            modifier = Modifier
                .constrainAs(tvLinkTweet) {
                    top.linkTo(tvTime.top)
                    bottom.linkTo(tvTime.bottom)
                    start.linkTo(tvTime.end, 5.dp)
                    width = Dimension.preferredWrapContent
                }
        )

        BorderLine(
            modifier = Modifier
                .constrainAs(borderPost) {
                    top.linkTo(tvLinkTweet.bottom, 15.dp)
                }
                .padding(horizontal = 20.dp)
                .height(0.33.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .constrainAs(btnFavorite) {
                    top.linkTo(borderPost.top, 15.dp)
                    start.linkTo(parent.start, 20.dp)
                }
                .clickable {

                }
        )

        Text(
            text = item.listUser.size.toString(),
            style = TextStyleApp.textColorBlack(13, TextAlign.Start),
            color = PostAppTheme.colors.primaryVariant,
            modifier = Modifier
                .constrainAs(tvTotalLike) {
                    top.linkTo(btnShowHide.top)
                    bottom.linkTo(btnShowHide.bottom)
                    start.linkTo(btnShowHide.end, 5.dp)
                    width = Dimension.preferredWrapContent
                }
        )

        BorderLine(
            modifier = Modifier
                .constrainAs(borderEnd) {
                    top.linkTo(btnFavorite.bottom, 20.dp)
                }
        )

    }
}

@Composable
@Preview
fun PreviewPostItem() {
    PostItem(
        item = PostWithFavorite(
            Post(
                iD_Post = 1,
                iD_User_Create = 2,
                content = " 12312313123 ",
                time_Create = "12/03/2023 12:45"
            ),
            User(),
            mutableListOf()
        )
    )
}