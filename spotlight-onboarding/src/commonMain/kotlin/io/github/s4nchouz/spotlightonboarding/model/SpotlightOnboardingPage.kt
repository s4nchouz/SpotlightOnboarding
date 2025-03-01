@file:OptIn(ExperimentalUuidApi::class)

package io.github.s4nchouz.spotlightonboarding.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
internal data class SpotlightOnboardingPage(
    val items: SnapshotStateMap<Uuid, SpotlightOnboardingItem> = SnapshotStateMap(),
)
