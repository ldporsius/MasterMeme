package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.impact
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.schemes_error
import nl.codingwithlinda.mastermeme.ui.theme.white
import org.jetbrains.compose.resources.Font

@Composable
fun MemeTextComponentActive(
    modifier: Modifier = Modifier,
    actionOnTapTwice: () -> Unit,
    actionOnDelete: () -> Unit
) {

    Box(modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ){


        Box(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        actionOnTapTwice()
                    },
                )
            }
            .padding(top = 24.dp, end = 24.dp)
            .border(width = 2.dp, color = black)
            ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Tap twice to edit",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily(
                        Font(Res.font.impact)
                    ),
                    textAlign = TextAlign.Justify,
                    color = black
                )
            }

        }

        IconButton(
            onClick = { actionOnDelete() },
            modifier = Modifier.align(Alignment.TopEnd),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = schemes_error
            )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = white
            )
        }
    }
}