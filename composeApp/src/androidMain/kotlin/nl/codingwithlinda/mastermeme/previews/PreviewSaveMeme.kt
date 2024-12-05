package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme.SaveMemeBottomSheet
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme.SaveMemeOption

@Preview
@Composable
fun PreviewSaveMemeOption() {
    SaveMemeOption(
        modifier = Modifier.fillMaxWidth(),
        icon = {
            Icon(Icons.Default.AccountCircle, contentDescription = null)
        },
        title = "Title",
        text = "Text",
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewSaveMemeBottomSheet(){
    SaveMemeBottomSheet(
        onDismiss = {},
        content = {
            SaveMemeOption(
                icon = {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                },
                title = "Title",
                text = "Text",
                onClick = {}
            )
        }
    )
}