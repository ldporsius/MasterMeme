package nl.codingwithlinda.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import nl.codingwithlinda.mastermeme.core.data.local_cache.DatabaseFactory
import nl.codingwithlinda.mastermeme.app.App
import nl.codingwithlinda.mastermeme.core.domain.local_cache.LocalCache
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.AndroidColorPicker
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.FontPicker
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPickerFactory
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.core.presentation.util.DateTimeUtils
import nl.codingwithlinda.mastermeme.meme_creator.presentation.meme_factory.AndroidMemeFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val picker = ShareAppPickerFactory()
            val databaseFactory = DatabaseFactory(this.applicationContext)

            App(
                shareAppPicker = picker.create(),
                imageConverter = ImageConverter(
                    context = this,
                    templates = MemeTemplatesFromResources()
                ),
                colorPicker = AndroidColorPicker(),
                fontPicker = FontPicker(),
                localCache = LocalCache(databaseFactory),
                memeFactory = AndroidMemeFactory(
                    dateTimeUtils = DateTimeUtils(),
                    fontPicker = FontPicker(),
                    ourPlatformTextStyle = nl.codingwithlinda.mastermeme.core.presentation.create_meme.OurPlatformTextStyle()
                ),
                internalStorageInteractor = nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor(this)
               )
        }
    }
}

