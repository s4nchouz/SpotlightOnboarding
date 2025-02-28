package io.github.s4nchouz.spotlightonboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingState

val LocalSpotlightOnboardingState = compositionLocalOf<SpotlightOnboardingState> {
    error("SpotlightOnboardingState not provided")
}

@Composable
fun ProvideSpotlightOnboardingState(
    state: SpotlightOnboardingState,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSpotlightOnboardingState provides state,
        content = content,
    )
}

