package com.example.postappcompose.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postappcompose.ui.textstyle.TextStyleApp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditText(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChange: (String) -> Unit,
    typeInput: KeyboardType = KeyboardType.Text,
    hint: String = "",
    errorText: String = "",
    isLastEditText: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(Color.Transparent),
            textStyle = TextStyleApp.textColorWhite(
                fontSize = 14,
                textAlign = TextAlign.Start
            ),
            onValueChange = { onTextChange(it) },
            value = text,
            placeholder = {
                Text(
                    text = hint,
                    color = Color.White.copy(alpha = 0.5f)
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                leadingIconColor = Color.Red,
                trailingIconColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorCursorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = if (isLastEditText) ImeAction.Done else ImeAction.Next,
                keyboardType = typeInput,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
            ),
        )
        Spacing(5)
        Text(
            text = errorText,
            style = TextStyleApp.textError,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
@Preview("default")
private fun PreviewEditText() {
    EditText(
        onTextChange = {},
        text = "TAs",
        errorText = "error1231231231231232313123123123123123123123123123213123"
    )
}