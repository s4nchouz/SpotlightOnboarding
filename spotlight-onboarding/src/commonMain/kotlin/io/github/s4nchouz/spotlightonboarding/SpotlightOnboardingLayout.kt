package io.github.s4nchouz.spotlightonboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingState
import io.github.s4nchouz.spotlightonboarding.model.rememberSpotlightOnboardingState

object SpotlightOnboardingDefaults {
    val SCRIM_COLOR = Color.Black.copy(alpha = 0.4f)
}

@Composable
fun SpotlightOnboarding(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    spotlightState: SpotlightOnboardingState = rememberSpotlightOnboardingState(),
    scrimColor: Color = SpotlightOnboardingDefaults.SCRIM_COLOR,
    content: @Composable BoxScope.() -> Unit,
) {
    LaunchedEffect(isVisible) {
        if (isVisible) {
            spotlightState.show()
        } else {
            spotlightState.hide()
        }
    }

    ProvideSpotlightOnboardingState(
        state = spotlightState,
    ) {
        Box(modifier = modifier) {
            content()

            Spotlight(
                state = spotlightState,
                scrimColor = scrimColor,
            )
        }
    }
}
