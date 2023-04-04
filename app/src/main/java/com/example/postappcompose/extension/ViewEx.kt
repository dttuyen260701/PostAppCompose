package com.example.postappcompose.extension

import android.view.View
import androidx.constraintlayout.compose.Visibility

/*
 * Created by tuyen.dang on 10/12/2022
 */

internal fun setVisibility(statement: Boolean): Visibility = if (statement) Visibility.Visible else Visibility.Gone