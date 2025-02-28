package util

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@NonRestartableComposable
fun StatusBarsSpacer(additional: Dp = 0.dp) = Spacer(
    modifier = Modifier.height(
        height = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() } + additional
    )
)

@Composable
@NonRestartableComposable
fun NavigationBarsSpacer(additional: Dp = 0.dp) = Spacer(
    modifier = Modifier.height(
        height = with(LocalDensity.current) { WindowInsets.navigationBars.getBottom(this).toDp() } + additional
    )
)

@Composable
@NonRestartableComposable
fun ColumnScope.Spacer(height: Dp) = Spacer(
    modifier = Modifier.height(height)
)

@Composable
@NonRestartableComposable
fun RowScope.Spacer(width: Dp) = Spacer(
    modifier = Modifier.width(width)
)
