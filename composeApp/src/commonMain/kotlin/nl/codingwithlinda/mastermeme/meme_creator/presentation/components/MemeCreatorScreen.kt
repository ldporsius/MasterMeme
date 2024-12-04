package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white
import kotlin.math.roundToInt

@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
    state: MemeCreatorViewState,
    onAction: (MemeCreatorAction) -> Unit,
) {
    Surface (modifier = modifier){

       /* val offsetX = remember(state.selectedMemeTextIndex) {
            mutableStateOf(0f)
        }
        val offsetY = remember(state.selectedMemeTextIndex) {
            mutableStateOf(0f)
        }*/
        var size by remember {
            mutableStateOf(Size.Zero)
        }
      /*  val pointerInputModifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        onAction(MemeCreatorAction.PositionText(
                            parentWidth = size.width,
                            parentHeight = size.height,
                            offsetX = offsetX.value,
                            offsetY = offsetY.value
                        ))
                    },
                ) { _, dragAmount ->
                    val original = Offset(offsetX.value, offsetY. value)
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-size.width/2, size.width - 100.dp.toPx()),
                        y = summed.y.coerceIn(0f, size.height - 50.dp.toPx())                     )
                    offsetX. value = newValue. x
                    offsetY. value = newValue. y
                }
            }*/

        Column {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .onSizeChanged {
                        size = Size(it.width.toFloat(), it.height.toFloat())
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onAction(MemeCreatorAction.StopEditing)
                            }
                        )
                    }

            ) {

                state.memeImageUi.DrawImage()

                state.memeTexts.onEach { memeText ->
                    val offset = IntOffset(
                        memeText.value.offsetX.roundToInt(),
                        memeText.value.offsetY.roundToInt()
                    )
                    if (memeText.value.isEditing) {
                        MemeTextComponentActive(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            text = memeText.value,
                            actionOnDelete = {
                                onAction(MemeCreatorAction.DeleteMemeText(memeText.key))
                            },
                            onAction = onAction
                        )

                    } else {
                        MemeTextComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                ,
                            text = memeText.value,
                            parentSize = size,
                            onAction = onAction
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            when(state.isEditing){
                true -> {
                    EditTextSizeComponent(
                        onAction = onAction
                    )
                }
                false -> {
                    CreatorButtonsComponent(
                        modifier = Modifier.fillMaxWidth(),
                        onAction = onAction
                    )
                }
            }
        }
    }
}