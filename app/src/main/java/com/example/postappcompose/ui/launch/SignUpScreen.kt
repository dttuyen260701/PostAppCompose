package com.example.postappcompose.ui.launch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.postappcompose.R
import com.example.postappcompose.ui.component.EditText
import com.example.postappcompose.ui.component.SingleButton
import com.example.postappcompose.ui.component.Spacing
import com.example.postappcompose.ui.textstyle.TextStyleApp
import com.example.postappcompose.ui.theme.PostAppTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    PostAppTheme.colors.backgroundGradient
                ),
            )
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacing(150)
        Text(
            text = stringResource(id = R.string.SignUpTvInfo),
            style = TextStyleApp.textColorWhite(18),
        )
        Spacing(75)
        Text(
            text = stringResource(id = R.string.LoginYourMail),
            style = TextStyleApp.textColorWhite(14, TextAlign.Start),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
        Spacing(10)
        EditText(
            text = "",
            onTextChange = {

            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            typeInput = KeyboardType.Email,
            errorText = "123"
        )
        Spacing(30)
        Text(
            text = stringResource(id = R.string.LoginYourPass),
            style = TextStyleApp.textColorWhite(14, TextAlign.Start),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
        Spacing(10)
        EditText(
            text = "",
            onTextChange = {

            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            typeInput = KeyboardType.Password,
            errorText = "123"
        )
        Spacing(70)
        SingleButton(
            onClick = {

            },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(54.dp)
                .fillMaxWidth(),
            textBtn = stringResource(id = R.string.SignUpBtnSignUp),
            backgroundColor = PostAppTheme.colors.backgroundButton
        )
        Spacing(30)
        Text(
            text = stringResource(id = R.string.SignUpMoveToLogin),
            style = TextStyleApp.textColorWhite(13),
        )
    }
}