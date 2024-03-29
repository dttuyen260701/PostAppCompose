package com.example.postappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = PostColors(
    primary = NavyBlue,
    primaryVariant = Teal200,
    colorNationBlue = UnitedNationBlue,
    secondary = NavyBlue,
    backgroundGradient = listOf(NavyBlue, NeonBlue, NavyBlue),
    backgroundButton = listOf(HotMagenta, DarkViolet)
)

private val LightColorPalette = PostColors(
    primary = NavyBlue,
    primaryVariant = Teal200,
    colorNationBlue = UnitedNationBlue,
    secondary = NavyBlue,
    backgroundGradient = listOf(NavyBlue, NeonBlue, NavyBlue),
    backgroundButton = listOf(HotMagenta, DarkViolet)
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

object PostAppTheme {
    val colors: PostColors
        @Composable
        get() = LocalPostColors.current
}

@Composable
fun PostAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }
    ProvidePostColors(colors = colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Stable
class PostColors(
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    colorNationBlue: Color,
    backgroundGradient: List<Color>,
    backgroundButton: List<Color>
) {
    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var colorNationBlue by mutableStateOf(colorNationBlue)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var backgroundGradient by mutableStateOf(backgroundGradient)
        private set
    var backgroundButton by mutableStateOf(backgroundButton)
        private set

    fun update(other: PostColors) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        colorNationBlue = other.colorNationBlue
        secondary = other.secondary
        backgroundGradient = other.backgroundGradient
        backgroundButton = other.backgroundButton
    }

    fun copy(): PostColors = PostColors(
        primary = primary,
        primaryVariant = primaryVariant,
        colorNationBlue = colorNationBlue,
        secondary = secondary,
        backgroundGradient = backgroundGradient,
        backgroundButton = backgroundButton
    )
}

@Composable
fun ProvidePostColors(
    colors: PostColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalPostColors provides  colorPalette, content = content)
}

private val LocalPostColors = staticCompositionLocalOf<PostColors> {
    error("No LocalPostColors provided")
}

fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)