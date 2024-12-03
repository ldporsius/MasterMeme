package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import org.jetbrains.compose.resources.Font

@Composable
fun MemeTextComponent(
    modifier: Modifier = Modifier,
    actionOnTap: () -> Unit,

    ) {

    Box(modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ){


        Box(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        actionOnTap()
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

    }
}