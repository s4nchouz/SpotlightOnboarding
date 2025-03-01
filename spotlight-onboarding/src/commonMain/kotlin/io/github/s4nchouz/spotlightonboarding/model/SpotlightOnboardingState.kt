@file:OptIn(ExperimentalUuidApi::class)

package io.github.s4nchouz.spotlightonboarding.model

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun rememberSpotlightOnboardingState(pageCount: Int = 1) = remember(pageCount) {
    SpotlightOnboardingState(
        pageCount = pageCount,
    )
}

@Stable
class SpotlightOnboardingState(
    private val pageCount: Int
) {
    internal val pages by mutableStateOf(
        value = SnapshotStateList<SpotlightOnboardingPage>().apply {
            repeat(pageCount) {
                add(element = SpotlightOnboardingPage())
            }
        }
    )

    var currentPageIndex: Int by mutableStateOf(0)
        private set

    var isVisible by mutableStateOf(false)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }

    fun nextPage() {
        if (currentPageIndex != pageCount - 1) {
            currentPageIndex++
        }
    }

    fun previewPage() {
        if (currentPageIndex != 0) {
            currentPageIndex--
        }
    }

    fun setPage(index: Int) {
        currentPageIndex = index
    }

    internal fun putItem(pageIndex: Int, key: Uuid, item: SpotlightOnboardingItem) {
        val page = pages[pageIndex]
        page.items[key] = item
    }

    internal fun removeItem(pageIndex: Int, key: Uuid) {
        val page = pages[pageIndex]
        page.items.remove(key)
    }
}

