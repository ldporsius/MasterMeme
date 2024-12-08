package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit.EditMemeBottomBar
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit.EditTextSizeComponent
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme
import nl.codingwithlinda.mastermeme.ui.theme.black

@Preview
@Composable
fun PreviewCreatorBottomBar() {

    AppTheme {
        EditMemeBottomBar(
            modifier = Modifier.fillMaxWidth()
                .background(color = black)
            ,
            changeTextStyleComponent = {},
            changeTextSizeComponent = {
                EditTextSizeComponent(
                    memeText = fakeMemeTextUI()
                ) { }
            },
            changeTextColorComponent = {},
            onAction = {}
        )

    }
}