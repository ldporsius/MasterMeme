package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ConfirmExitDialog(
    onDismiss: ()-> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = onDismiss){
        Surface {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Leave editor?",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "You will lose your precious meme. If you're fine with that, press ‘Leave’.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End
                ) {
                    Button(onClick = onDismiss){
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onConfirm){
                        Text(text = "Leave")
                    }
                }

            }
        }

    }
}