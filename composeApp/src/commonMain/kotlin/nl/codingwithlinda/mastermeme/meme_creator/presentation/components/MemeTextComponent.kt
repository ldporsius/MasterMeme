package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.impact
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.black
import org.jetbrains.compose.resources.Font
import kotlin.math.roundToInt

@Composable
fun MemeTextComponent(
    modifier: Modifier = Modifier,
    text: MemeUiText,
    parentSize: Size,
    onAction: (MemeCreatorAction) -> Unit,

    ) {

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
                    onAction(MemeCreatorAction.StartEditing(text.id))
                },
                onDoubleTap = {
                    onAction(MemeCreatorAction.SelectMemeText(text.id))
                }
            )
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDragEnd = {
                    onAction(
                        MemeCreatorAction.PositionText(
                            id = text.id,
                            parentWidth = parentSize.width,
                            parentHeight = parentSize.height,
                            offsetX = offsetX.value,
                            offsetY = offsetY.value
                        ))
                },
            ) { _, dragAmount ->
                val original = Offset(offsetX.value, offsetY. value)
                val summed = original + dragAmount
                val newValue = Offset(
                    x = summed.x.coerceIn(-parentSize.width/2, parentSize.width - 100.dp.toPx()),
                    y = summed.y.coerceIn(0f, parentSize.height - 50.dp.toPx())                     )
                offsetX. value = newValue. x
                offsetY. value = newValue. y
            }
        }

    val pointerOffset = androidx.compose.ui.unit.IntOffset(
        offsetX.value.roundToInt(),
        offsetY.value.roundToInt()
    )
    Box(modifier = modifier
        .offset { pointerOffset }
        .then(pointerInputModifier),
        contentAlignment = Alignment.TopEnd
    ){


        Box(modifier = Modifier
            .fillMaxWidth()

            .padding(top = 24.dp, end = 24.dp)
            //.border(width = 2.dp, color = black)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text.text,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily(
                        Font(Res.font.impact)
                    ),
                    fontSize = TextUnit(text.fontSize, TextUnitType.Sp),
                    textAlign = TextAlign.Justify,
                    color = black
                )
            }

        }

    }
}