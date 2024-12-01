package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.core.presentation.MemeTemplatesImpl
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun MemeCreatorRoot() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ){
        paddingValues ->
        MemeCreatorScreen(
            modifier = Modifier.padding(paddingValues),
            state = MemeCreatorViewState(
                memeImageUi = MemeTemplatesImpl.getTemplates().first()
            )
        )

    }
}