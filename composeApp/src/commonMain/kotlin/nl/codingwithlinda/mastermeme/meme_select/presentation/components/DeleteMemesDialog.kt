package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DeleteMemesDialog(
    numberToDelete: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        modifier = modifier,
        title = { Text(
            "Delete $numberToDelete memes?") },
        text = { Text(
            "You will not be able to restore them. If you're fine with that, press 'Delete'.") },
        dismissButton = {
            Button(onClick = onDismiss){
                Text("Cancel")
            }
        },
        confirmButton = {
            Button(onClick = onConfirm){
                Text("Delete")
            }
        },
    )
}