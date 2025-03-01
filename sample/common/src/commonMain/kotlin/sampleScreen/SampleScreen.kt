package sampleScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.s4nchouz.spotlightonboarding.SpotlightOnboarding
import io.github.s4nchouz.spotlightonboarding.model.rememberSpotlightOnboardingState
import theme.SpotlightTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen() {
    var isVisible by remember { mutableStateOf(true) }
    val spotlightOnboardingState = rememberSpotlightOnboardingState(pageCount = 2)

    SpotlightOnboarding(
        modifier = Modifier.fillMaxSize(),
        isVisible = isVisible,
        onDismissRequest = { isVisible = false },
        sheetDragHandle = null,
        sheetContainerColor = SpotlightTheme.color.background,
        spotlightState = spotlightOnboardingState,
        sheetContent = {
            SampleSheetContent(
                spotlightOnboardingState = spotlightOnboardingState,
                onCloseClicked = { isVisible = false },
            )
        }
    ) {
        SampleScreenContent(
            modifier = Modifier.fillMaxSize(),
            isVisible = isVisible,
            onShowClicked = { isVisible = true }
        )
    }
}