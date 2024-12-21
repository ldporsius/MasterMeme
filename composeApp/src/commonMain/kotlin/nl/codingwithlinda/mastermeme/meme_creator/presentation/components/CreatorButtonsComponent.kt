package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.text_input.MemeTextInputParent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction

@Composable
fun CreatorButtonsComponent(
    modifier: Modifier = Modifier,
    fontUi: FontUi,
    isAdding: Boolean = false,
    onAction: (MemeCreatorAction) -> Unit
) {

    var text by remember {
        mutableStateOf("")
    }
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(isAdding) {
        if (isAdding)
        focusRequester.requestFocus()
    }
    val focusModifier = Modifier.focusRequester(focusRequester)


        AnimatedContent(
            targetState = isAdding) { adding ->

            if (adding) {
                Box(modifier = Modifier.padding(16.dp)) {
                    MemeTextInputParent(
                        modifier = Modifier
                            .fillMaxWidth()
                                then (focusModifier),
                        text = text,
                        setText = {
                            text = it
                        },
                        fontUi = fontUi,
                        actionOnDismiss = { onAction(MemeCreatorAction.CancelAddText) },
                        actionOnConfirm = {
                            onAction(MemeCreatorAction.CreateText(text))
                        },
                    )
                }
            } else {

                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(onClick = {
                        text = ""
                        onAction(MemeCreatorAction.AddText)
                    }) {
                        Text(text = "Add text")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { onAction(MemeCreatorAction.StartSaveMeme) }) {
                        Text(text = "Save meme")
                    }
                }
            }
        }
}