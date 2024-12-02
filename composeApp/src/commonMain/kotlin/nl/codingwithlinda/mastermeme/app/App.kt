package nl.codingwithlinda.mastermeme.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import nl.codingwithlinda.mastermeme.meme_creator.presentation.MemeCreatorRoot
import nl.codingwithlinda.mastermeme.memes_list.presentation.MemesListRoot
import nl.codingwithlinda.mastermeme.navigation.Route
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

@Composable
fun App() {

    val navController = rememberNavController()
    AppTheme {
        NavHost(navController = navController, startDestination = Route.mainGraph){
            navigation<Route.mainGraph>(startDestination = Route.MemeList){
                composable<Route.MemeList>(){
                    MemesListRoot(
                        navToMemeCreator = {
                            navController.navigate(Route.MemeCreator(it))
                        }
                    )
                }
                composable<Route.MemeCreator>(){entry ->
                    val memeId = entry.toRoute<Route.MemeCreator>().memeId
                    MemeCreatorRoot(
                        memeId = memeId,
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }

    }
}