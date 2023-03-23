package com.example.postappcompose.ui.textstyle

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


object TextStyleApp {
    fun textColorWhite(
        fontSize: Int = 13,
        textAlign: TextAlign = TextAlign.Center
    ) : TextStyle {
        return TextStyle(
            color = Color.White,
            fontSize = fontSize.sp,
            textAlign = textAlign,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif,
            background = Color.Transparent
        )
    }
    val textError = TextStyle(
        color = Color.Red,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Start,
        background = Color.Transparent,
        fontFamily = FontFamily.Serif
    )
}