package sampleScreen

import Strings
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import io.github.s4nchouz.spotlightonboarding.spotlightItem
import sampleScreen.model.textItems
import theme.SpotlightTheme
import util.Spacer
import util.StatusBarsSpacer

@Composable
internal fun SampleScreenContent(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onShowClicked: () -> Unit,
) {
    val rowItems = remember { textItems() }
    val columnItems = remember { textItems() + textItems() }

    Column(modifier = modifier.background(color = SpotlightTheme.color.background)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    state = rememberScrollState(),
                    enabled = isVisible.not()
                ),
        ) {
            StatusBarsSpacer(additional = 16.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        state = rememberScrollState(),
                        enabled = isVisible.not()
                    ),
            ) {
                Spacer(width = 16.dp)

                rowItems.fastForEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .let {
                                when (index) {
                                    2 -> it.spotlightItem(
                                        cornerRadius = 8.dp,
                                        pageIndex = 0,
                                    )

                                    4 -> it.spotlightItem(
                                        cornerRadius = 8.dp,
                                        pageIndex = 1
                                    )

                                    else -> it
                                }
                            }
                            .background(color = SpotlightTheme.color.card, shape = RoundedCornerShape(16.dp))
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Text(
                            text = text,
                            color = SpotlightTheme.color.textPrimary,
                            fontSize = 16.sp
                        )
                    }

                    if (index != rowItems.lastIndex) {
                        Spacer(width = 8.dp)
                    }
                }

                Spacer(width = 16.dp)
            }

            Spacer(height = 16.dp)

            columnItems.fastForEachIndexed { index, text ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .let {
                            when (index) {
                                2 -> it.spotlightItem(
                                    cornerRadius = 8.dp,
                                    pageIndex = 0,
                                )

                                4 -> it.spotlightItem(
                                    cornerRadius = 8.dp,
                                    pageIndex = 1
                                )

                                else -> it
                            }
                        }
                        .fillMaxWidth()
                        .background(color = SpotlightTheme.color.card, shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = text,
                        color = SpotlightTheme.color.textPrimary,
                        fontSize = 24.sp
                    )

                    Text(
                        text = text,
                        color = SpotlightTheme.color.textSecondary,
                        fontSize = 16.sp
                    )
                }

                if (index != columnItems.lastIndex) {
                    Spacer(height = 8.dp)
                }
            }

            Spacer(height = 16.dp)
        }

        HorizontalDivider(thickness = 0.5.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .navigationBarsPadding()
                .background(color = SpotlightTheme.color.primary, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onShowClicked)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = Strings.SHOW_BUTTON,
                color = SpotlightTheme.color.onPrimary,
                fontSize = 16.sp
            )
        }
    }
}