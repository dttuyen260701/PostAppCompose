package com.example.postappcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postappcompose.R
import com.example.postappcompose.ui.textstyle.TextStyleApp

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
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.White)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onLeftIconClick,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f, false)
                .padding(5.dp),
            enabled = (leftIcon != null)
        ) {
            leftIcon?.let {
                Icon(
                    painterResource(id = it),
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    contentDescription = null
                )
            }
        }

        Text(
            text = title,
            style = TextStyleApp.textTitleBlack()
        )

        IconButton(
            onClick = onRightIconClick,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f, false)
                .padding(5.dp),
            enabled = (rightIcon != null)
        ) {
            rightIcon?.let {
                Icon(
                    painterResource(id = it),
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    contentDescription = null)
            }
        }
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