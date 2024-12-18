package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativeCanvas
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.OurPlatformTextStyle
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeTextState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.schemes_error
import nl.codingwithlinda.mastermeme.ui.theme.white
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun MemeTextComponent(
    text: MemeUiText,
    platformTextStyle: OurPlatformTextStyle,
    parentSize: Size,
    onAction: (MemeCreatorAction) -> Unit,
) {
    val textMeasurer = rememberTextMeasurer()

    val fontFamily = FontFamily(
        text.fontResource.font
    )
    val _fontSize = TextUnit(text.fontSize, TextUnitType.Sp)

    val iconButtonSize = 24.dp

    val textStyle = TextStyle(
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
    val textHeight = textLayoutResult.size.height

    val offsetX = remember() {
        mutableStateOf(text.offsetX)
    }
    val offsetY = remember() {
        mutableStateOf(text.offsetY)
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
                    println("DRAG END. parentWidth: ${parentSize.width}, parentHeight: ${parentSize.height}")
                    println("DRAG END. offsetX: ${offsetX.value}, offsetY: ${offsetY.value}")
                    println("DRAG END. text width: $textWidth , text height: $textHeight")
                    println("DRAG END. firstBaseline: ${textLayoutResult.firstBaseline}")
                    println("DRAG END. lastBaseline: ${textLayoutResult.lastBaseline}")

                    onAction(
                        MemeCreatorAction.PositionText(
                            id = text.id,
                            parentWidth = parentSize.width,
                            parentHeight = parentSize.height,
                            offsetX = offsetX.value,
                            offsetY = offsetY.value
                        )
                    )
                },
            ) { _, dragAmount ->
                val original = Offset(offsetX.value, offsetY.value)
                val summed = original + dragAmount
                val maxX = (parentSize.width - textWidth).coerceAtLeast(0f)
                val maxY = (parentSize.height - textLayoutResult.lastBaseline).coerceAtLeast(0f)
                val newValue = Offset(
                    x = summed.x.coerceIn(0f, maxX),
                    y = summed.y.coerceIn(-textLayoutResult.firstBaseline * .5f, maxY)
                )
                offsetX.value = newValue.x
                offsetY.value = newValue.y
            }
        }

    val pointerOffset = IntOffset(
        offsetX.value.roundToInt(),
        (offsetY.value).roundToInt()
    )

    val border: Modifier = remember(text.memeTextState) {
        if (text.memeTextState == MemeTextState.Selected)
            Modifier
                .border(width = 2.dp, color = white)
                .padding(4.dp)
        else Modifier

    }

    Text(
        modifier = Modifier
            //.wrapContentSize()
            .offset { pointerOffset }
            .then(border)
            .then(pointerInputModifier),
        text = text.text,
        style = textStyle,
        onTextLayout = {
            println("MEME TEXT COMPONENT has new TEXT LAYOUT size: ${it.size}")
            //textLayoutResult = it
        }
    )

    val iconButtonModifier = Modifier.
    offset {
        IntOffset(
            x = (pointerOffset.x + textWidth - iconButtonSize.roundToPx())
                .coerceAtMost(parentSize.toDpSize().width.roundToPx() -  2 * iconButtonSize.roundToPx()),
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
