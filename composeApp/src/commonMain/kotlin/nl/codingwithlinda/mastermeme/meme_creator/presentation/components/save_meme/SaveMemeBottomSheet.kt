package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.download
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeBottomSheet
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SaveMemeBottomSheet(
    onAction: (MemeCreatorAction) -> Unit,
    onShare: () -> Unit
) {
    MemeBottomSheet(
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
                            imageVector = vectorResource(Res.drawable.download),
                            contentDescription = null
                        )
                    },
                    title = "Save meme",
                    text = "Save created meme in the Files of your device",
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
                    text = "Share your meme or open it in the other App",
                    onClick = {
                        onShare()
//                        state.memeUri?.let {
//                            shareAppPicker.share(imageUri = state.memeUri)
//                        }
                    }
                )
            }
        }
    )
}