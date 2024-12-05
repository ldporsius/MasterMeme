package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.border
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.impact
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.schemes_error
import nl.codingwithlinda.mastermeme.ui.theme.white
import org.jetbrains.compose.resources.Font

@Composable
fun MemeTextComponentActive(
    modifier: Modifier = Modifier,
    text: MemeUiText,
    actionOnDelete: () -> Unit,
    onAction: (MemeCreatorAction) -> Unit,
) {

    Box(modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ){


        Box(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {

            }
            .padding(top = 24.dp, end = 24.dp)
            .border(width = 2.dp, color = black)
            ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                OutlinedTextField(
                    value = text.text,
                    onValueChange = {
                        onAction(MemeCreatorAction.EditMemeText(text.id, it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = white,
                        unfocusedTextColor = black,
                        focusedContainerColor = white,
                        focusedTextColor = black
                    ),
                    textStyle = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = TextUnit(text.fontSize, TextUnitType.Sp),
                        fontFamily = FontFamily(
                            Font(Res.font.impact)
                        )
                    )
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