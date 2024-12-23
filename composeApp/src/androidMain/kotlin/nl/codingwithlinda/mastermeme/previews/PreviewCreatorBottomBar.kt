package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text.CustomizeMemeTextBottomBar
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text.CustomizeTextSizeComponent
import nl.codingwithlinda.mastermeme.previews.data_generator.fakeMemeTextUI
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme
import nl.codingwithlinda.mastermeme.ui.theme.black

@Preview
@Composable
fun PreviewCreatorBottomBar() {

    AppTheme {
        CustomizeMemeTextBottomBar(
            modifier = Modifier.fillMaxWidth()
                .background(color = black)
            ,
            changeTextRotationComponent = {},
            changeTextStyleComponent = {},
            changeTextSizeComponent = {
                CustomizeTextSizeComponent(
                    memeText = fakeMemeTextUI()
                ) { }
            },
            changeTextColorComponent = {},
            onAction = {}
        )

    }
}