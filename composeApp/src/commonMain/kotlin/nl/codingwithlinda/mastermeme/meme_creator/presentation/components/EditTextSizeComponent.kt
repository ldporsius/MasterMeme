package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun EditTextSizeComponent(
    memeText: MemeUiText,
    onAction: (MemeCreatorAction) -> Unit
) {

    var sliderValue by remember {
        mutableStateOf(memeText.fontSize)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            onAction(MemeCreatorAction.UndoTextSize(memeText.id))
            onAction(MemeCreatorAction.StopEditing)
        }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
        Slider(
            modifier = Modifier.weight(1f),
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            },
            steps = 10,
            valueRange = 0f..100f,
            onValueChangeFinished = {
                onAction(MemeCreatorAction.AdjustTextSize(memeText.id, sliderValue))
            },
        )
        IconButton(onClick = {
            onAction(MemeCreatorAction.StopEditing)
        }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
        }
    }
}