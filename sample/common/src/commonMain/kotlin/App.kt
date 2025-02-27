import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.s4nchouz.spotlightOnboarding.SpotlightOnboarding
import io.github.s4nchouz.spotlightOnboarding.spotlightItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var isVisible by remember { mutableStateOf(true) }

    SpotlightOnboarding(
        modifier = Modifier.fillMaxSize(),
        isVisible = isVisible,
        onDismissRequest = { isVisible = false },
        sheetDragHandle = null,
        sheetContent = {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Sheet content"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(), enabled = isVisible.not()),
        ) {
            repeat(30) {
                Text(text = it.toString())
            }

            Button(
                modifier = Modifier
                    .spotlightItem(
                        cornerRadius = 16.dp
                    ),
                onClick = { isVisible = true }
            ) {
                Text(text = "CLick")
            }

            repeat(100) {
                Text(text = it.toString())
            }
        }
    }
}