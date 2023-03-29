package com.example.postappcompose.ui.launch

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.postappcompose.R
import com.example.postappcompose.ui.component.EditText
import com.example.postappcompose.ui.component.SingleButton
import com.example.postappcompose.ui.component.Spacing
import com.example.postappcompose.ui.textstyle.TextStyleApp.textColorWhite
import com.example.postappcompose.ui.theme.PostAppTheme
import com.example.postappcompose.utils.Constant

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInSuccess: () -> Unit,
    onMoveToSignUp: () -> Unit,
    launchViewModel: LaunchViewModel
) {
    val context = LocalContext.current
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
            text = email.value,
            onTextChange = {
                email.value = it
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            hint = stringResource(id = R.string.LoginYourMail),
            typeInput = KeyboardType.Email,
            errorText = emailError.value
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
            hint = stringResource(id = R.string.LoginYourPass),
            typeInput = KeyboardType.Password,
            errorText = passwordError.value,
            isLastEditText = true
        )
        Spacing(20)
        SingleButton(
            onClick = {
                firstClick.value = false
                if(emailError.value == "" && passwordError.value == "") {
                    launchViewModel.loginUser(
                        email = email.value,
                        pass = password.value,
                        onStart = {

                        },
                        onResult = {result ->
                            if(result != null) {
                                Constant.userWithFavorite = result
                                Toast.makeText(
                                    context,
                                    R.string.LoginLoginSuccess,
                                    Toast.LENGTH_LONG
                                ).show()
                                onSignInSuccess()
                                launchViewModel.clearData()
                            } else {
                                Toast.makeText(
                                    context,
                                    R.string.LoginLoginFail,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    )
                }
            },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(54.dp)
                .fillMaxWidth(),
            textBtn = stringResource(id = R.string.LoginBtnLogin),
            backgroundColor = PostAppTheme.colors.backgroundButton
        )
        Spacing(20)
        Text(
            modifier = Modifier
                .clickable {
                    onMoveToSignUp()
                },
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
@Preview
fun PreviewLoginScreen() {
    SignInScreen(modifier = Modifier,{}, {}, hiltViewModel())
}