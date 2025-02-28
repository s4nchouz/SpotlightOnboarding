package sampleScreen

import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.SpotlightTheme
import util.Spacer

@Composable
internal fun ColumnScope.SampleSheetContent(
    onCloseClicked: () -> Unit,
) {
    Spacer(height = 16.dp)

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = Strings.SHEET_TITLE,
        color = SpotlightTheme.color.textPrimary,
        fontSize = 24.sp
    )

    Spacer(height = 2.dp)

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = Strings.SHEET_SUBTITLE,
        color = SpotlightTheme.color.textSecondary,
        fontSize = 16.sp
    )

    Spacer(height = 16.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = SpotlightTheme.color.primary, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onCloseClicked)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = Strings.CLOSE_BUTTON,
            color = SpotlightTheme.color.onPrimary,
            fontSize = 16.sp
        )
    }

    Spacer(height = 16.dp)
}
