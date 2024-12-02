package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction

@Composable
fun CreatorButtonsComponent(
    modifier: Modifier = Modifier,
    onAction: (MemeCreatorAction) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        OutlinedButton(onClick = { onAction(MemeCreatorAction.AddText) }) {
            Text(text = "Add text")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = { onAction(MemeCreatorAction.SaveMeme) }) {
            Text(text = "Save meme")
        }
    }
}