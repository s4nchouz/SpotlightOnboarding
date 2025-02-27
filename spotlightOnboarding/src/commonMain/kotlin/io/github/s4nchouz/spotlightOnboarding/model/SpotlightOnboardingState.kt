@file:OptIn(ExperimentalUuidApi::class)

package io.github.s4nchouz.spotlightOnboarding.model

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun rememberSpotlightOnboardingState() = remember {
    SpotlightOnboardingState()
}

@Stable
class SpotlightOnboardingState {
    internal val items by mutableStateOf(SnapshotStateMap<Uuid, SpotlightOnboardingItem>())

    var isVisible by mutableStateOf(false)
        private set

    internal fun show() {
        isVisible = true
    }

    internal fun hide() {
        isVisible = false
    }

    internal fun setItem(key: Uuid, item: SpotlightOnboardingItem) {
        items[key] = item
    }

    internal fun removeItem(key: Uuid) {
        items.remove(key)
    }
}

