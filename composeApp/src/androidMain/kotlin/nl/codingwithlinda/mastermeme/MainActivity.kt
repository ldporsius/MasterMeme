package nl.codingwithlinda.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import nl.codingwithlinda.mastermeme.app.App
import nl.codingwithlinda.mastermeme.core.presentation.contact_picker.ContactPickerFactory
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.AndroidColorPicker
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPickerFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val picker = ShareAppPickerFactory()

            App(
                shareAppPicker = picker.create(),
                imageConverter = ImageConverter(this),
                colorPicker = AndroidColorPicker()
            )
        }
    }
}

