package io.github.s4nchouz.spotlightOnboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.s4nchouz.spotlightOnboarding.model.SpotlightOnboardingState
import io.github.s4nchouz.spotlightOnboarding.model.rememberSpotlightOnboardingState

object SpotlightOnboardingDefaults {
    val SCRIM_COLOR = Color.Black.copy(alpha = 0.4f)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotlightOnboarding(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    spotlightState: SpotlightOnboardingState = rememberSpotlightOnboardingState(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    sheetShape: Shape = BottomSheetDefaults.ExpandedShape,
    sheetContainerColor: Color = BottomSheetDefaults.ContainerColor,
    sheetContentColor: Color = contentColorFor(sheetContainerColor),
    sheetTonalElevation: Dp = 0.dp,
    scrimColor: Color = SpotlightOnboardingDefaults.SCRIM_COLOR,
    sheetDragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    sheetContentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    sheetProperties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    onDismissRequest: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
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
                onDismissRequest = onDismissRequest,
                sheetState = sheetState,
                sheetMaxWidth = sheetMaxWidth,
                shape = sheetShape,
                containerColor = sheetContainerColor,
                contentColor = sheetContentColor,
                tonalElevation = sheetTonalElevation,
                scrimColor = Color.Transparent,
                dragHandle = sheetDragHandle,
                contentWindowInsets = sheetContentWindowInsets,
                properties = sheetProperties,
                content = sheetContent,
            )
        }
    }
}
