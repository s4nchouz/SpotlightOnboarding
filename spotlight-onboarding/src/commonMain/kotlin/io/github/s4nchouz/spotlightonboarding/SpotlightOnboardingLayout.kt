package io.github.s4nchouz.spotlightonboarding

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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

/**
 * A composable function that provides a guided onboarding experience using a spotlight effect.
 *
 * This function highlights specific UI elements with a spotlight effect, allowing users to focus on
 * important parts of the interface. It supports customizable transitions and scrim colors.
 *
 * @param modifier Modifier to be applied to the root container.
 * @param isVisible Determines whether the onboarding is currently visible.
 * @param spotlightState The state object that controls the spotlight effect.
 * @param spotlightEnterTransition The enter transition for the spotlight effect.
 * @param spotlightExitTransition The exit transition for the spotlight effect.
 * @param scrimColor The color of the background scrim when the spotlight is active.
 * @param content A composable function defining the main content of the screen.
 */
@Composable
fun SpotlightOnboarding(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    spotlightState: SpotlightOnboardingState = rememberSpotlightOnboardingState(),
    spotlightEnterTransition: EnterTransition = fadeIn(),
    spotlightExitTransition: ExitTransition = fadeOut(),
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
                enterTransition = spotlightEnterTransition,
                exitTransition = spotlightExitTransition,
            )
        }
    }
}
