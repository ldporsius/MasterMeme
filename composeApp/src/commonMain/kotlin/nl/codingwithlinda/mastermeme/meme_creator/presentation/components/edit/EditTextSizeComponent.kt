package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextSizeComponent(
    memeText: MemeUiText,
    onAction: (MemeCreatorAction) -> Unit
) {

    var sliderValue by remember {
        mutableStateOf(memeText.fontSize)
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {

        Text("Aa",
            style = MaterialTheme.typography.labelSmall)

        Slider(
            modifier = Modifier.weight(1f),
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            },
            steps = 0,
            valueRange = 0.1f..10f,
            onValueChangeFinished = {
                onAction(MemeCreatorAction.AdjustTextSize(memeText.id, sliderValue))
            },
            thumb = {
                Box(modifier = Modifier
                    .size(24.dp)
                    .background(brush = Brush.radialGradient(
                        colors = listOf(black, white.copy(0.5f)),
                    ),
                        shape = androidx.compose.foundation.shape.CircleShape
                    ),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ){
                    Spacer(modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                        .padding(4.dp)
                    )
                }

            },
            track = {
                HorizontalDivider()
            }
        )
        Text("Aa",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = TextUnit(28f, TextUnitType.Sp)
        )

       /* IconButton(onClick = {
            onAction(MemeCreatorAction.StopEditing)
        }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
        }*/
    }
}