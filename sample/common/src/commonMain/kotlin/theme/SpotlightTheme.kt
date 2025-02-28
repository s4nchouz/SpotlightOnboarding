package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun SpotlightTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) darkColors() else lightColors()

    CompositionLocalProvider(
        LocalSpotlightColors provides colors,
        content = content,
    )
}

object SpotlightTheme {

    val color: SpotlightColors
        @Composable
        @ReadOnlyComposable
        get() = LocalSpotlightColors.current
}