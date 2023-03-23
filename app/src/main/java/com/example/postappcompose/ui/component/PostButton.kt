package com.example.postappcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.postappcompose.ui.textstyle.TextStyleApp

@Composable
fun SingleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textBtn: String = "",
    backgroundColor: List<Color>
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = Modifier
            .then(modifier)
            .clip(CircleShape)
            .background(
                Brush.horizontalGradient(
                    backgroundColor
                ),
            ),
        elevation = null
    ) {
        Text(
            text = textBtn,
            style = TextStyleApp.textColorWhite(14)
        )
    }
}