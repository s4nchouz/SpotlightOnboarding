package io.github.s4nchouz.spotlightonboarding

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingState
import io.github.s4nchouz.spotlightonboarding.model.rememberSpotlightOnboardingState

/**
 * A composable function that provides a guided onboarding experience using a spotlight effect and a bottom sheet.
 *
 * This function highlights specific UI elements with a spotlight effect while displaying additional information
 * in a modal bottom sheet. It supports customizable transitions, scrim colors, and bottom sheet properties.
 *
 * @param modifier Modifier to be applied to the root container.
 * @param isVisible Determines whether the onboarding is currently visible.
 * @param spotlightState The state object that controls the spotlight effect.
 * @param spotlightEnterTransition The enter transition for the spotlight effect.
 * @param spotlightExitTransition The exit transition for the spotlight effect.
 * @param scrimColor The color of the background scrim when the spotlight is active.
 * @param sheetState The state object that controls the bottom sheet's behavior.
 * @param sheetMaxWidth The maximum width of the bottom sheet.
 * @param sheetShape The shape of the bottom sheet.
 * @param sheetContainerColor The background color of the bottom sheet.
 * @param sheetContentColor The content color inside the bottom sheet.
 * @param sheetTonalElevation The elevation of the bottom sheet.
 * @param sheetDragHandle A composable function for rendering the drag handle of the bottom sheet.
 * @param sheetContentWindowInsets A lambda providing window insets for the bottom sheet.
 * @param sheetProperties Additional properties for configuring the modal bottom sheet.
 * @param onDismissRequest A callback triggered when the bottom sheet is dismissed.
 * @param sheetContent A composable function defining the content inside the bottom sheet.
 * @param content A composable function defining the main content of the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotlightOnboarding(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    spotlightState: SpotlightOnboardingState = rememberSpotlightOnboardingState(),
    spotlightEnterTransition: EnterTransition = fadeIn(),
    spotlightExitTransition: ExitTransition = fadeOut(),
    scrimColor: Color = SpotlightOnboardingDefaults.SCRIM_COLOR,
    sheetState: SheetState = rememberModalBottomSheetState(),
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    sheetShape: Shape = BottomSheetDefaults.ExpandedShape,
    sheetContainerColor: Color = BottomSheetDefaults.ContainerColor,
    sheetContentColor: Color = contentColorFor(sheetContainerColor),
    sheetTonalElevation: Dp = 0.dp,
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
                enterTransition = spotlightEnterTransition,
                exitTransition = spotlightExitTransition,
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
