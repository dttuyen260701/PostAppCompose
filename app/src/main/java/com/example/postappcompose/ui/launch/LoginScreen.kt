package com.example.postappcompose.ui.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postappcompose.R
import com.example.postappcompose.ui.component.EditText
import com.example.postappcompose.ui.component.SingleButton
import com.example.postappcompose.ui.component.Spacing
import com.example.postappcompose.ui.textstyle.TextStyleApp.textColorWhite
import com.example.postappcompose.ui.theme.PostAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    PostAppTheme.colors.backgroundGradient
                ),
            )
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacing(100)
        Image(
            painter = painterResource(id = R.drawable.man_u),
            contentDescription = "Man U",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacing(40)
        Text(
            text = stringResource(id = R.string.LoginTvWelcome),
            style = textColorWhite(18),
        )
        Spacing(15)
        Text(
            text = stringResource(id = R.string.LoginTvNotification),
            style = textColorWhite(13),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacing(50)
        EditText(
            text = "",
            onTextChange = {

            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            hint = stringResource(id = R.string.LoginYourMail),
            typeInput = KeyboardType.Email,
            errorText = "123"
        )
        Spacing(10)
        EditText(
            text = "",
            onTextChange = {

            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            hint = stringResource(id = R.string.LoginYourPass),
            typeInput = KeyboardType.Password,
            errorText = "123",
            isLastEditText = true
        )
        Spacing(20)
        SingleButton(
            onClick = {

            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(54.dp)
                .fillMaxWidth(),
            textBtn = stringResource(id = R.string.LoginBtnLogin),
            backgroundColor = PostAppTheme.colors.backgroundButton
        )
        Spacing(20)
        Text(
            text = stringResource(id = R.string.SignUpBtnSignUp),
            style = textColorWhite(13)
        )
        Spacing(20)
        Text(
            text = stringResource(id = R.string.LoginForgotPass),
            style = textColorWhite(13)
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewLoginScreen() {
    LoginScreen()
}