package nl.codingwithlinda.mastermeme.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import nl.codingwithlinda.mastermeme.app.di.DispatcherProvider
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.local_cache.LocalCache
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.ColorPicker
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.FontPicker
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory
import nl.codingwithlinda.mastermeme.meme_creator.presentation.MemeCreatorRoot
import nl.codingwithlinda.mastermeme.meme_select.presentation.MemeSelectRoot
import nl.codingwithlinda.mastermeme.memes_list.presentation.MemesListRoot
import nl.codingwithlinda.mastermeme.navigation.Route
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

@Composable
fun App(
    dispatcherProvider: DispatcherProvider,
    shareAppPicker: ShareAppPicker,
    imageConverter: ImageConverter,
    colorPicker: ColorPicker,
    fontPicker: FontPicker,
    localCache: LocalCache,
    memeFactory: MemeFactory,
    internalStorageInteractor: InternalStorageInteractor
) {


    val navController = rememberNavController()
    AppTheme {
        NavHost(navController = navController, startDestination = Route.mainGraph){
            navigation<Route.mainGraph>(startDestination = Route.MemeList){
                composable<Route.MemeList>(){
                    MemesListRoot(
                        dispatcherProvider = dispatcherProvider,
                        storageInteractor = localCache.storageInteractor(),
                        internalStorageInteractor = internalStorageInteractor,
                        navToMemeCreator = {
                            navController.navigate(Route.MemeCreator(it)){
                                launchSingleTop = true

                            }
                        },
                       shareAppPicker = shareAppPicker
                    )
                }
                composable<Route.MemeCreator>(){entry ->
                    val memeId = entry.toRoute<Route.MemeCreator>().memeId
                    MemeCreatorRoot(
                        memeId = memeId,
                        dispatcherProvider = dispatcherProvider,
                        shareAppPicker = shareAppPicker,
                        imageConverter = imageConverter,
                        colorPicker = colorPicker,
                        fontPicker = fontPicker,
                        storageInteractor = localCache.storageInteractor(),
                        memeFactory = memeFactory,
                        onBack = {
                            navController.navigateUp()
                        },

                    )
                }

            }
        }

    }
}