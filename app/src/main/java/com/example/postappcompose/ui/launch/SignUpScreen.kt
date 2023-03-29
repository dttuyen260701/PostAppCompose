package com.example.postappcompose.ui.launch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.postappcompose.R
import com.example.postappcompose.data.models.User
import com.example.postappcompose.ui.component.EditText
import com.example.postappcompose.ui.component.SingleButton
import com.example.postappcompose.ui.component.Spacing
import com.example.postappcompose.ui.textstyle.TextStyleApp
import com.example.postappcompose.ui.theme.PostAppTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpSuccess: () -> Unit,
    onMoveToSignIn: () -> Unit,
    launchViewModel: LaunchViewModel
) {
    val email = remember {
        LaunchViewModel.email
    }
    val emailError = remember {
        derivedStateOf {
            launchViewModel.validEmail(email.value)
        }
    }
    val password = remember {
        LaunchViewModel.password
    }
    val passwordError = remember {
        derivedStateOf {
            launchViewModel.validPassWord(password.value)
        }
    }
    val firstClick = remember {
        launchViewModel.firstClickButton
    }
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacing(10)
        EditText(
            text = email.value,
            onTextChange = {
                email.value = it
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            typeInput = KeyboardType.Email,
            errorText = emailError.value
        )
        Spacing(30)
        Text(
            text = stringResource(id = R.string.LoginYourPass),
            style = TextStyleApp.textColorWhite(14, TextAlign.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacing(10)
        EditText(
            text = password.value,
            onTextChange = {
                password.value = it
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            typeInput = KeyboardType.Password,
            errorText = passwordError.value,
            isLastEditText = true
        )
        Spacing(70)
        SingleButton(
            onClick = {
                firstClick.value = false
                launchViewModel.insertUser(
                    User(
                        email = email.value.trim(),
                        password = password.value.trim(),
                        name = email.value.trim().split("@")[0]
                    ),
                    onStart = {

                    },
                    onResult = {
                        onSignUpSuccess()
                    }
                )
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
            modifier = Modifier
                .clickable {
                    onMoveToSignIn()
                },
            text = stringResource(id = R.string.SignUpMoveToLogin),
            style = TextStyleApp.textColorWhite(13),
        )
    }
}