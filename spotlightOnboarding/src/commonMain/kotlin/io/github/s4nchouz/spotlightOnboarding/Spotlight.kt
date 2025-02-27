package io.github.s4nchouz.spotlightOnboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.*
import io.github.s4nchouz.spotlightOnboarding.model.SpotlightOnboardingState
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun Spotlight(
    scrimColor: Color,
    state: SpotlightOnboardingState,
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = state.isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val spotlightPath = Path().apply {
                    state.items.values.forEach { spotlightItem ->
                        val positionInParent = spotlightItem.layoutCoordinates.positionInRoot()

                        val left = spotlightItem.contentPadding.calculateLeftPadding(layoutDirection).toPx()
                        val top = spotlightItem.contentPadding.calculateTopPadding().toPx()
                        val right = spotlightItem.contentPadding.calculateRightPadding(layoutDirection).toPx()
                        val bottom = spotlightItem.contentPadding.calculateBottomPadding().toPx()

                        if (spotlightItem.layoutCoordinates.isAttached) {
                            addRoundRect(
                                roundRect = RoundRect(
                                    left = positionInParent.x - left,
                                    top = positionInParent.y - top,
                                    right = positionInParent.x + spotlightItem.layoutCoordinates.size.width + right,
                                    bottom = positionInParent.y + spotlightItem.layoutCoordinates.size.height + bottom,
                                    radiusX = spotlightItem.cornerRadius.toPx(),
                                    radiusY = spotlightItem.cornerRadius.toPx(),
                                ),
                            )
                        }
                    }
                }

                clipPath(
                    path = spotlightPath,
                    clipOp = ClipOp.Difference,
                ) {
                    drawRect(
                        brush = SolidColor(scrimColor),
                        alpha = 1f,
                    )
                }
            },
        )
    }
}
