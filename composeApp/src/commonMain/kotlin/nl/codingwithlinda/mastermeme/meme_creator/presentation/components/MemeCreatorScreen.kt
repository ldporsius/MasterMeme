package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme.SaveMemeBottomSheet
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme.SaveMemeOption
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
    state: MemeCreatorViewState,
    shareAppPicker: ShareAppPicker,
    onAction: (MemeCreatorAction) -> Unit,
) {

    shareAppPicker.registerPicker {
        it.let {
            println("Is This Called?: $it")
        }
    }

    Surface (modifier = modifier

    ){

        var size by remember {
            mutableStateOf(Size.Zero)
        }


        Column {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .onSizeChanged {
                        size = Size(it.width.toFloat(), it.height.toFloat())
                        onAction(MemeCreatorAction.SaveParentSize(it.width.toFloat(), it.height.toFloat()))
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onAction(MemeCreatorAction.StopEditing)
                            }
                        )
                    }

            ) {

                state.memeImageUi.DrawImage()

                state.memeTexts.onEach { memeText ->
                    if (memeText.value.isEditing) {
                        MemeTextComponentActive(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            text = memeText.value,
                            actionOnDelete = {
                                onAction(MemeCreatorAction.DeleteMemeText(memeText.key))
                            },
                            onAction = onAction
                        )

                    } else {
                        MemeTextComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                            ,
                            text = memeText.value,
                            parentSize = size,
                            onAction = onAction
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            when(state.isEditing){
                true -> {
                    state.selectedMemeText?.let {
                        EditTextSizeComponent(
                            memeText = it,
                            onAction = onAction
                        )
                    }
                }
                false -> {
                    CreatorButtonsComponent(
                        modifier = Modifier.fillMaxWidth(),
                        onAction = onAction
                    )
                }
            }
        }

        AnimatedVisibility(state.isSaving){
            SaveMemeBottomSheet(
                onDismiss = {
                    onAction(MemeCreatorAction.CancelSaveMeme)
                },
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        SaveMemeOption(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null
                                )
                            },
                            title = "Save meme",
                            text = "Keep this meme in your local storage",
                            onClick = {
                                onAction(MemeCreatorAction.SaveMeme)
                            }
                        )
                        SaveMemeOption(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = null
                                )
                            },
                            title = "Share meme",
                            text = "Share this meme with your friends",
                            onClick = {
                                state.memeUri?.let {
                                    println("MEME CREATOR SCREEN HAS URI: $it")
                                    shareAppPicker.share(imageUri = state.memeUri)
                                }
                            }
                        )
                    }
                }
            )
        }

    }
}