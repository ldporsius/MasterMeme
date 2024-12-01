package nl.codingwithlinda.mastermeme.memes_list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.core.presentation.MemeUi

@Composable
fun MemeListScreen(
    memes: List<MemeUi>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().safeContentPadding(),
        bottomBar = {
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End,

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "Memes")
            if(memes.isEmpty()){
                EmptyListComponent(
                    modifier = Modifier.fillMaxSize()
                )
            }
            MemeListAdaptiveLayout(
                memes = memes,
                onMemeClick = {

                }
            )
        }
    }
}