package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.color_picker_button
import mastermeme.composeapp.generated.resources.text_size_button
import mastermeme.composeapp.generated.resources.text_style_button
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomizeMemeTextBottomBar(
    modifier: Modifier = Modifier,
    changeTextStyleComponent: @Composable () -> Unit,
    changeTextSizeComponent: @Composable () -> Unit,
    changeTextColorComponent: @Composable () -> Unit,
    onAction: (MemeCreatorAction) -> Unit
) {
    var customizeTextOption: CustomizeTextOption? by remember {
        mutableStateOf(null)
    }

    Column {
        AnimatedVisibility(visible = customizeTextOption != null){
            when(customizeTextOption){
                CustomizeTextOption.TEXTSTYLE -> {
                    changeTextStyleComponent()
                }
                CustomizeTextOption.TEXTSIZE -> {
                    changeTextSizeComponent()
                }
                CustomizeTextOption.TEXTCOLOR -> {
                    changeTextColorComponent()
                }
                null -> Unit
            }
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = {
                    customizeTextOption = null
                    onAction(MemeCreatorAction.UndoAll)
                    onAction(MemeCreatorAction.StopEditing)
                }
            ){
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.text_style_button),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                        customizeTextOption = CustomizeTextOption.TEXTSTYLE
                    }
                )
                Image(
                    painter = painterResource(Res.drawable.text_size_button),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                        customizeTextOption = CustomizeTextOption.TEXTSIZE
                    }
                )
                Image(
                    painter = painterResource(Res.drawable.color_picker_button),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                        customizeTextOption = CustomizeTextOption.TEXTCOLOR
                    }
                )
            }
            IconButton(
                onClick = {
                    customizeTextOption = null
                    onAction(MemeCreatorAction.StopEditing)
                }
            ){
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }

        }
    }
}