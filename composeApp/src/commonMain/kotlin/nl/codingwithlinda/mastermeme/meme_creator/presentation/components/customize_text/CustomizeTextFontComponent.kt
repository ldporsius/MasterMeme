package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi

@Composable
fun CustomizeTextFontComponent(
    modifier: Modifier = Modifier,
    fonts: List<FontUi>,
    onFontSelected: (index: Int) -> Unit,
    onLineThrough: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .horizontalScroll(rememberScrollState())
        ,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        fonts.forEachIndexed {index, it ->
            EditTextFontItem(
                modifier = Modifier.clickable {
                    onFontSelected(index)
                },
                text = "GOOD",
                name = it.name,
                font = it.font,
                textDecoration = it.textDecoration
            )
        }

        EditTextFontItem(
            modifier = Modifier.clickable {
              onLineThrough()
            },
            text = "GOOD",
            name = "Linethrough",
            font = fonts[0].font,
            textDecoration = TextDecoration.LineThrough
        )
    }
}

@Composable
fun EditTextFontItem(
    modifier: Modifier = Modifier,
    text: String,
    name: String,
    font: Font,
    textDecoration: TextDecoration
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = font.toFontFamily()
        )
        Text(text = name,
            style = MaterialTheme.typography.labelMedium,
            fontFamily = font.toFontFamily(),
            textDecoration = textDecoration
        )
    }

}