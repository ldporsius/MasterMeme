package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.text_input.MemeTextInputParent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMemeTextBottomSheet(
    state: MemeCreatorViewState,
    onAction: (MemeCreatorAction) -> Unit
) {
    val _text = state.editingMemeText ?: return

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