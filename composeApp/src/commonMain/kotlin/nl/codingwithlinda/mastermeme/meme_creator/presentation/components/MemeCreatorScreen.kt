package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.PictureDrawer
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.core.presentation.model.bitMapImageModifier
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text.CustomizeTextComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme.SaveMemeBottomSheet
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.text_input.MemeTextInputParent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
    state: MemeCreatorViewState,
    colors: List<androidx.compose.ui.graphics.Color>,
    fonts: List<FontUi>,
    shareAppPicker: ShareAppPicker,
    onAction: (MemeCreatorAction) -> Unit,
) {

    shareAppPicker.registerPicker {
        println("Is This Called?: $it")
    }
    var size by remember {
        mutableStateOf( Size.Zero )
    }

    Surface (modifier = modifier){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .wrapContentSize()
                    .weight(1f)
                    .onSizeChanged {
                        size = Size(it.width.toFloat(), it.height.toFloat())
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onAction(MemeCreatorAction.StopEditing)
                            }
                        )
                    }

            ) {
                if (state.isSaving){
                    PictureDrawer(
                        modifier = Modifier,
                        content = {
                            MemeTextsBox(
                                parentSize = size,
                                memeTemplate = state.memeImageUi,
                                memeTexts = state.memeTexts.values.toList(),
                                onAction = onAction,
                                modifier = bitMapImageModifier
                            )
                        },
                        onSave = {
                            onAction(MemeCreatorAction.CreateMemeUri(it))
                        }
                    )
                }
                else {
                    MemeTextsBox(
                        parentSize = size,
                        memeTemplate = state.memeImageUi,
                        memeTexts = state.memeTexts.values.toList(),
                        onAction = onAction,
                        modifier = bitMapImageModifier
                    )
                }
            }

            Box(modifier = Modifier) {
                when (state.shouldShowCustomizeTextOptions) {
                    true -> {
                        CustomizeTextComponent(
                            state = state,
                            colors = colors,
                            fonts = fonts,
                            onAction = onAction
                        )
                    }

                    false -> {
                        CreatorButtonsComponent(
                            modifier = Modifier.fillMaxWidth(),
                            fontUi = fonts[0],
                            isAdding = state.isAddingText,
                            onAction = onAction
                        )
                    }
                }
            }
        }

        AnimatedVisibility(state.editingMemeText != null){
            val _text = state.editingMemeText ?: return@AnimatedVisibility

            ModalBottomSheet(
                onDismissRequest = {
                    onAction(MemeCreatorAction.UndoEditing(_text.id))
                },
                content = {
                    MemeTextInputParent(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = _text.text,
                        setText = {
                            onAction(MemeCreatorAction.EditMemeText(_text.id, it))
                        },
                        fontUi = _text.fontResource,
                        actionOnDismiss = {
                            onAction(MemeCreatorAction.UndoEditing(_text.id))
                        },
                        actionOnConfirm = {
                            onAction(MemeCreatorAction.StopEditing)
                        },
                    )
                }
            )
        }
        AnimatedVisibility(state.isSaving){
           SaveMemeBottomSheet(
               onAction = onAction,
               onShare = {
                   state.memeUri?.let {
                       shareAppPicker.share(imageUri = state.memeUri)
                   }
               }
           )
        }

    }
}