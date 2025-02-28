@file:OptIn(ExperimentalUuidApi::class)

package io.github.s4nchouz.spotlightonboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingItem
import io.github.s4nchouz.spotlightonboarding.model.SpotlightOnboardingState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Applies a spotlight effect to a composable item, making it a target for the onboarding spotlight.
 *
 * This modifier registers the composable as a spotlight item, allowing it to be highlighted
 * during the onboarding experience. The spotlight effect can be customized with corner radius
 * and padding.
 *
 * @param cornerRadius The corner radius of the spotlight effect. Defaults to `0.dp` (sharp edges).
 * @param contentPadding The padding around the spotlighted item. Defaults to `PaddingValues()`.
 */
@Stable
fun Modifier.spotlightItem(
    cornerRadius: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues()
) = composed {
    this then SpotlightItemElement(
        state = LocalSpotlightOnboardingState.current,
        cornerRadius = cornerRadius,
        contentPadding = contentPadding,
    )
}

private class SpotlightItemElement(
    private val state: SpotlightOnboardingState,
    private val cornerRadius: Dp = 0.dp,
    private val contentPadding: PaddingValues = PaddingValues(),
) : ModifierNodeElement<SpotlightItemNode>() {

    override fun create() = SpotlightItemNode(
        state = state,
        cornerRadius = cornerRadius,
        contentPadding = contentPadding,
    )

    override fun update(node: SpotlightItemNode) {
        node.cornerRadius = cornerRadius
        node.state = state
        node.contentPadding = contentPadding
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SpotlightItemElement) return false

        if (cornerRadius != other.cornerRadius) return false
        if (contentPadding != other.contentPadding) return false
        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cornerRadius.hashCode()
        result = 31 * result + contentPadding.hashCode()
        result = 31 * result + state.hashCode()
        return result
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "spotlightItem"
        properties["cornerRadius"] = cornerRadius
        properties["contentPadding"] = contentPadding
    }
}

private class SpotlightItemNode(
    var state: SpotlightOnboardingState,
    var cornerRadius: Dp = 0.dp,
    var contentPadding: PaddingValues = PaddingValues(),
) : Modifier.Node(), GlobalPositionAwareModifierNode {

    val key = Uuid.random()

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        state.putItem(
            key = key,
            item = SpotlightOnboardingItem(
                layoutCoordinates = coordinates,
                cornerRadius = cornerRadius,
                contentPadding = contentPadding,
            )
        )
    }

    override fun onDetach() {
        state.removeItem(
            key = key
        )

        super.onDetach()
    }
}
