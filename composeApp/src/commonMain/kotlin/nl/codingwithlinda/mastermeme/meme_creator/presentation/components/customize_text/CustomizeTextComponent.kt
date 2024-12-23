package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun CustomizeTextComponent(
    state: MemeCreatorViewState,
    colors: List<Color>,
    fonts: List<FontUi>,
    onAction: (MemeCreatorAction) -> Unit
) {
    if (state.selectedMemeText == null) return

    CustomizeMemeTextBottomBar(
        modifier = Modifier.fillMaxWidth(),
        changeTextRotationComponent = {
            CustomizeTextRotationComponent(
                memeText = state.selectedMemeText,
                onAction = onAction
            )
        },
        changeTextStyleComponent = {
            CustomizeTextFontComponent(
                fonts = fonts,
                onFontSelected = {
                    onAction(
                        MemeCreatorAction.EditMemeTextFont(
                            state.selectedMemeText.id,
                            it
                        )
                    )
                },
               onLineThrough = {
                   onAction(
                       MemeCreatorAction.EditMemeTextLineThrough
                   )
               }
            )
        },
        changeTextSizeComponent = {
            CustomizeTextSizeComponent(
                memeText = state.selectedMemeText,
                onAction = onAction
            )
        },
        changeTextColorComponent = {
            CustomizeTextColorComponent(
                modifier = Modifier.padding(16.dp),
                colors = colors,
                onColorSelected = {
                    onAction(
                        MemeCreatorAction.EditMemeTextColor(
                            state.selectedMemeText.id,
                            it
                        )
                    )
                }
            )
        },
        onAction = onAction
    )
}