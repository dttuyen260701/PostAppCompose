package com.example.postappcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Spacing(height: Int = 0) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(Color.Transparent)
    )
}

@Composable
fun BorderLine(modifier: Modifier = Modifier) {
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .then(modifier)
            .fillMaxWidth()
            .background(Color.Gray)
    )
}