package io.github.s4nchouz.spotlightOnboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.s4nchouz.spotlightOnboarding.model.SpotlightOnboardingState
import io.github.s4nchouz.spotlightOnboarding.model.rememberSpotlightOnboardingState

object SpotlightOnboardingDefaults {
    const val delay = 400L
    val scrimColor = Color.Black.copy(alpha = 0.4f)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotlightOnboarding(
    modifier: Modifier = Modifier,
    delay: Long = SpotlightOnboardingDefaults.delay,
    isVisible: Boolean = false,
    scrimColor: Color = SpotlightOnboardingDefaults.scrimColor,
    spotlightState: SpotlightOnboardingState = rememberSpotlightOnboardingState(),
    onDismiss: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
    )

    LaunchedEffect(isVisible) {
        if (isVisible) {
            spotlightState.show()
            sheetState.show()
        } else {
            spotlightState.hide()
            sheetState.hide()
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

        if (isVisible) {
            ModalBottomSheet(
                dragHandle = null,
                sheetState = sheetState,
                scrimColor = Color.Transparent,
                onDismissRequest = onDismiss,
                content = sheetContent,
            )
        }
    }
}
