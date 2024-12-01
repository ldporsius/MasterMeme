package nl.codingwithlinda.mastermeme.memes_list.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EmptyListComponent(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
       // val img = imageResource(Res.drawable.vector_18)

        Image(
            imageVector = vectorResource(Res.drawable.vector_18),
            contentDescription = null
        )
    }
}