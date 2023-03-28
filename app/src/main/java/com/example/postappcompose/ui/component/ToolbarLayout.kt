package com.example.postappcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postappcompose.ui.textstyle.TextStyleApp
import com.example.postappcompose.R

@Composable
fun ToolbarView(
    modifier: Modifier = Modifier,
    title: String = "",
    @DrawableRes rightIcon: Int? = null,
    onRightIconClick: () -> Unit = {},
    @DrawableRes leftIcon: Int? = null,
    onLeftIconClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = onRightIconClick,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f, false)
        ) {
            rightIcon?.let {
                Icon(painterResource(id = it), contentDescription = null)
            }
        }

        Text(
            text = title,
            style = TextStyleApp.textTitleBlack()
        )
    }
}

@Composable
@Preview()
fun PreviewLoginScreen() {
    ToolbarView(
        modifier = Modifier.height(56.dp),
        title = "Test",
        rightIcon = R.drawable.ic_logout
    )
}