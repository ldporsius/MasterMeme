package nl.codingwithlinda.mastermeme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.mastermeme.meme_creator.presentation.MemeCreatorRoot
import nl.codingwithlinda.mastermeme.memes_list.presentation.MemesListRoot
import nl.codingwithlinda.mastermeme.navigation.Route
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

@Composable
fun App() {

    val navController = rememberNavController()
    AppTheme {
        NavHost(navController = navController, startDestination = "start"){
            navigation(startDestination = Route.MemeList.route, route = "start"){
                composable(route = Route.MemeList.route){
                    MemesListRoot()
                }
                composable(route = Route.MemeCreator.route){
                    MemeCreatorRoot()
                }
            }
        }

    }
}