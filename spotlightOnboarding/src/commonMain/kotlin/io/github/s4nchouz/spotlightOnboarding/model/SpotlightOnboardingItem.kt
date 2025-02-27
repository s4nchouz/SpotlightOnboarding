package io.github.s4nchouz.spotlightOnboarding.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Dp

@Immutable
internal data class SpotlightOnboardingItem(
    val layoutCoordinates: LayoutCoordinates,
    val cornerRadius: Dp,
    val contentPadding: PaddingValues,
)
