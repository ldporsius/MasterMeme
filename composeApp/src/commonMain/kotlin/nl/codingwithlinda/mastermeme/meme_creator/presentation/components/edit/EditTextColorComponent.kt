package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EditTextColorComponent(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    onColorSelected: (Color) -> Unit
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (color in colors) {
           Spacer(
               modifier = Modifier
                   .background(color = color, shape = CircleShape)
                   .border(width = Dp.Hairline, color = Color.White, shape = CircleShape)
                   .size(48.dp)
                   .clickable {
                       onColorSelected(color)
                   }
           )

        }
    }
}