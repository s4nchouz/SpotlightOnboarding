package io.github.s4nchouz.spotlightOnboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import io.github.s4nchouz.spotlightOnboarding.model.SpotlightOnboardingState

internal val LocalSpotlightOnboardingState = compositionLocalOf<SpotlightOnboardingState> {
    error("SpotlightOnboardingState not provided")
}

@Composable
internal fun ProvideSpotlightOnboardingState(
    state: SpotlightOnboardingState,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSpotlightOnboardingState provides state,
        content = content,
    )
}

