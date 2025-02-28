package io.github.s4nchouz.spotlightonboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingState

private const val STATE_NOT_PROVIDED_MESSAGE = "SpotlightOnboardingState not provided"

val LocalSpotlightOnboardingState = compositionLocalOf<SpotlightOnboardingState> {
    error(STATE_NOT_PROVIDED_MESSAGE)
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

