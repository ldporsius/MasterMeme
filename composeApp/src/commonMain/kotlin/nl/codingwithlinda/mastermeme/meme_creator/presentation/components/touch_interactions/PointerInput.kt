package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.touch_interactions

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.impact
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import org.jetbrains.compose.resources.Font
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun PointerInput(
    text: MemeUiText,
    parentSize: Size,
    onAction: (MemeCreatorAction) -> Unit,
    content: @Composable () -> Unit
) {

    val fontFamily = FontFamily(
        Font(Res.font.impact)
    )

    val textMeasurer = rememberTextMeasurer()

    val fontSize = text.fontSize.sp
    val textStyle = TextStyle(
        fontSize = TextUnit(fontSize.value, TextUnitType.Sp),
        fontFamily = fontFamily,
        lineHeight = TextUnit(fontSize.value, TextUnitType.Sp),
    )
    val textSize = remember(text.fontSize) {
       // println("MEME TEXT COMPONENT has new font size: ${fontSize}")
        textMeasurer.measure(
            text= text.text,
            style = textStyle
        ).size
    }

    val textWidth = textSize.width
    val textHeight = textSize.height

    val offsetX = remember() {
        mutableStateOf(text.offsetX)
    }
    val offsetY = remember() {
        mutableStateOf(text.offsetY)
    }
    val pointerInputModifier = Modifier
        /*.pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    onAction(MemeCreatorAction.SelectMemeText(text.id))
                },
                onDoubleTap = {
                    onAction(MemeCreatorAction.StartEditing(text.id))
                }
            )
        }*/
        .pointerInput(Unit) {
            detectDragGestures(
                onDragEnd = {
                    println("DRAG END. offsetX: ${offsetX.value}, offsetY: ${offsetY.value}")
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
                val newValue = Offset(
                    x = summed.x.coerceIn(0f, parentSize.width - textWidth),
                    y = summed.y.coerceIn(0f, parentSize.height - textHeight)
                )
                offsetX.value = newValue.x
                offsetY.value = newValue.y
            }
        }
    val pointerOffset = androidx.compose.ui.unit.IntOffset(
        offsetX.value.roundToInt(),
        ceil( offsetY.value).roundToInt()
    )


    Box(modifier = Modifier
        .offset { pointerOffset }
        .then(pointerInputModifier)
    ){
        content()
    }


}