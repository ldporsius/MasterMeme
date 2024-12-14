package nl.codingwithlinda.mastermeme.meme_save.presentation

//import nl.codingwithlinda.mastermeme.meme_save.presentation.MemeSaveViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory
import nl.codingwithlinda.mastermeme.meme_save.presentation.components.MemeSaveScreen
import nl.codingwithlinda.mastermeme.meme_save.presentation.state.MemeSaveViewState

@Composable
fun MemeSaveRoot(
    memeId: String,
    imageConverter: ImageConverter,
    memeFactory: MemeFactory
) {
    //val viewModel = viewModel<MemeSaveViewModel>()

    Scaffold {

        MemeSaveScreen(
            viewState = MemeSaveViewState()
        )
    }

}