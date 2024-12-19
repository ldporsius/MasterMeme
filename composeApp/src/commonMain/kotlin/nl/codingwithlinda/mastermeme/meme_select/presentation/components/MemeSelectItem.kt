package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white

@Composable
fun MemeSelectItem(
    memeUi: MemeUi,
    isSelected: Boolean,
    onClick: (memeId: String) -> Unit,
) {

    Box(
        contentAlignment = androidx.compose.ui.Alignment.TopEnd
    ) {
        memeUi.image.DrawThumbnail()

        Box(modifier = Modifier
            .matchParentSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        black.copy(alpha = .5f),
                        Color.Transparent
                    )
                )
            )
            .padding(16.dp),
            contentAlignment = androidx.compose.ui.Alignment.TopEnd
        ) {
            if (isSelected) {
                SelectedIcon(
                    onClick = {
                        onClick(memeUi.id)
                    }
                )
            } else {
                UnSelectedIcon(
                    onClick = {
                        onClick(memeUi.id)
                    }
                )
            }
        }
    }
}

@Composable
fun UnSelectedIcon(
    onClick: () -> Unit,
) {
    Spacer(
        modifier = Modifier
            .size(24.dp)
            .padding(2.dp)
            .background(color = black.copy(.05f), shape = androidx.compose.foundation.shape.CircleShape)
            .border(width = 2.dp, color = white, shape = androidx.compose.foundation.shape.CircleShape)
            .clickable {
                onClick()
            }
    )
}
@Composable
fun SelectedIcon(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(2.dp)
            .background(color = black, shape = androidx.compose.foundation.shape.CircleShape)
    ) {

        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick()
                },
            tint = white
        )
    }
}