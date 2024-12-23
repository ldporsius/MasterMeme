package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.OurPlatformTextStyle
import nl.codingwithlinda.mastermeme.core.presentation.util.applyIf
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeTextState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.schemes_error
import nl.codingwithlinda.mastermeme.ui.theme.white
import kotlin.math.roundToInt

@Composable
fun MemeTextComponent(
    text: MemeUiText,
    platformTextStyle: OurPlatformTextStyle,
    parentSize: IntSize,
    onAction: (MemeCreatorAction) -> Unit,
) {

    val textMeasurer = rememberTextMeasurer()

    val fontFamily = FontFamily(
        text.fontResource.font
    )

    val currentFontSize = LocalTextStyle.current.fontSize.value
    val ratioFont = (currentFontSize / parentSize.height) * 100
    val scaledFontSize = (currentFontSize / ratioFont) * text.fontSize
    val _fontSize = TextUnit(scaledFontSize, TextUnitType.Sp)

    val iconButtonSize = 24.dp

    val textStyle = LocalTextStyle.current.merge(
        color = text.textColor,
        fontSize = _fontSize,
        fontFamily = fontFamily,
        lineHeight = _fontSize,
        platformStyle = platformTextStyle.PlTextStyle(),
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both,
        ),
    )

    val textLayoutResult = remember(text) {
        textMeasurer.measure(
            text = text.text,
            style = textStyle,
            overflow = TextOverflow.Clip,
            constraints = Constraints(
                maxWidth = parentSize.width.toInt(),
                maxHeight = parentSize.height.toInt()
            )
        )
    }

    val textWidth = textLayoutResult.size.width

    val offsetX = remember(parentSize) {
        mutableStateOf(text.offsetX * parentSize.width)
    }
    val offsetY = remember(parentSize) {
        mutableStateOf(text.offsetY * parentSize.height)
    }

    val pointerInputModifier = Modifier
        .pointerInput(textLayoutResult) {
            detectTapGestures(
                onTap = {
                    onAction(MemeCreatorAction.SelectMemeText(text.id))
                },
                onDoubleTap = {
                    onAction(MemeCreatorAction.StartEditing(text.id))
                }
            )
        }
        .pointerInput(textLayoutResult) {
            detectDragGestures(
                onDragEnd = {
                    onAction(
                        MemeCreatorAction.PositionText(
                            id = text.id,
                            offsetX = offsetX.value / parentSize.width,
                            offsetY = offsetY.value / parentSize.height
                        )
                    )
                },
                onDragCancel = {
                    onAction(
                        MemeCreatorAction.PositionText(
                            id = text.id,
                            offsetX = offsetX.value,
                            offsetY = offsetY.value
                        )
                    )
                }
            ) { change, dragAmount ->

                val original = Offset(offsetX.value, offsetY.value)
                val summed = original + dragAmount
                val maxX = (parentSize.width - textWidth).coerceAtLeast(0).toFloat()
                val maxY = (parentSize.height - textLayoutResult.lastBaseline).coerceAtLeast(0f)
                val newValue = Offset(
                    x = summed.x.coerceIn(0f, maxX),
                    y = summed.y.coerceIn(-textLayoutResult.firstBaseline * .5f, maxY)
                )
                offsetX.value = newValue.x
                offsetY.value = newValue.y
            }
        }

    val pointerOffset =
        IntOffset(
            (offsetX.value).roundToInt(),
            (offsetY.value).roundToInt()
        )

    val shouldShowBorder = text.memeTextState == MemeTextState.Selected

    Text(
        modifier = Modifier
            .offset {
               pointerOffset
            }
            .rotate(text.rotation)
            .then(pointerInputModifier)
            .applyIf(shouldShowBorder){
                Modifier.border(width = 2.dp, color = white)
                    .padding(4.dp)
            }
        ,
        text = text.text,
        style = textStyle,
        textDecoration = text.fontResource.textDecoration

    )

    val iconButtonModifier = Modifier.
    offset {
        IntOffset(
            x = (pointerOffset.x + textWidth - iconButtonSize.roundToPx())
                .coerceAtMost(parentSize.width -  2 * iconButtonSize.roundToPx()),
            y = pointerOffset.y - iconButtonSize.roundToPx()
        )
    }

    if (text.memeTextState == MemeTextState.Selected){
        IconButton(
            onClick = {
                onAction(MemeCreatorAction.DeleteMemeText(text.id))
            },
            modifier =
            iconButtonModifier
                .then(pointerInputModifier)
            ,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = schemes_error
            )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .size(iconButtonSize),
                tint = white
            )
        }
    }

}
