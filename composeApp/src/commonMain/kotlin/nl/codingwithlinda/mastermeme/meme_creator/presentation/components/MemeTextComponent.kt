package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun MemeTextComponent(
    text: MemeUiText,
    parentSize: Size,
    onAction: (MemeCreatorAction) -> Unit,
) {

    val fontFamily = FontFamily(
        text.fontResource.font
    )

    val textMeasurer = rememberTextMeasurer()

    val fontSize = text.fontSize.sp


        val textStyle = androidx.compose.material3.LocalTextStyle.current.merge(
            fontSize = fontSize,
            fontFamily = fontFamily,
            lineHeight = fontSize,
            //platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Top,
                trim = LineHeightStyle.Trim.Both,
            ),

        )
        val textSize = remember(text.fontSize) {
            println("MEME TEXT COMPONENT has new font size: ${fontSize}")
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

        val textWidth = textSize.size.width
        val textHeight = textSize.size.height

        val offsetX = remember() {
            mutableStateOf(text.offsetX)
        }
        val offsetY = remember() {
            mutableStateOf(text.offsetY)
        }

        val pointerInputModifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onAction(MemeCreatorAction.SelectMemeText(text.id))
                    },
                    onDoubleTap = {
                        onAction(MemeCreatorAction.StartEditing(text.id))
                    }
                )
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        println("DRAG END. parentWidth: ${parentSize.width}, parentHeight: ${parentSize.height}")
                        println("DRAG END. offsetX: ${offsetX.value}, offsetY: ${offsetY.value}")
                        println("DRAG END.  text width: $textWidth , text height: $textHeight")
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
                    val maxY = (parentSize.height - textHeight).coerceAtLeast(0f)
                    val newValue = Offset(
                        x = summed.x.coerceIn(0f, maxX),
                        y = summed.y.coerceIn(0f, maxY)
                    )
                    offsetX.value = newValue.x
                    offsetY.value = newValue.y
                }
            }

        val pointerOffset = androidx.compose.ui.unit.IntOffset(
            offsetX.value.roundToInt(),
            ceil(offsetY.value).roundToInt()
        )

        Text(
            modifier = Modifier
                .wrapContentSize()
//                .padding(0.dp)
                .offset { pointerOffset }
                .then(pointerInputModifier),
            text = text.text,
            style = textStyle,
            color = text.textColor,
            onTextLayout = {
                println("MEME TEXT COMPONENT has new TEXT LAYOUT size: ${it.size}")
            }
        )

}
