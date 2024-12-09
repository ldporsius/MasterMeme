package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white

@Composable
fun MemeTextInput(
    text: String,
    setText: (String) -> Unit,
    fontUi: FontUi,
    actionOnDismiss: () -> Unit,
    actionOnConfirm: (text: String) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                actionOnDismiss()
            }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
            IconButton(onClick = {
                actionOnConfirm(text)

            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }

        OutlinedTextField(
            value = text,
            onValueChange = {
                setText(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = false,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = white,
                unfocusedTextColor = black,
                focusedContainerColor = white.copy(alpha = 0.5f),
                focusedTextColor = black
            ),
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                fontSize = TextUnit(25f, TextUnitType.Sp),
                fontFamily = FontFamily(
                    fontUi.font
                )
            )
        )
    }
}