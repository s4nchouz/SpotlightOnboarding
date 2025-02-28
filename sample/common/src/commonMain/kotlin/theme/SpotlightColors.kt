package theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SpotlightColors(
    val primary: Color = Color(0xff009a00),
    val onPrimary: Color = Color.White,
    val textPrimary: Color,
    val textSecondary: Color,
    val background: Color,
    val card: Color,
)

@Stable
internal fun lightColors() = SpotlightColors(
    textPrimary = Color.Black,
    textSecondary = Color.Black.copy(alpha = 0.66f),
    background = Color(0xffedeef0),
    card = Color.White,
)

@Stable
internal fun darkColors() = SpotlightColors(
    textPrimary = Color.White,
    textSecondary = Color.White.copy(alpha = 0.66f),
    background = Color(0xff141414),
    card = Color.Black,
)

internal val LocalSpotlightColors = staticCompositionLocalOf {
    lightColors()
}
