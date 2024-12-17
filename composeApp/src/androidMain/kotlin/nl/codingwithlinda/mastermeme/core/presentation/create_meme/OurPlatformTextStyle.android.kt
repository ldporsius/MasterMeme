package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.ui.text.PlatformTextStyle

actual class OurPlatformTextStyle {
    actual fun PlTextStyle(): PlatformTextStyle {
        return PlatformTextStyle(includeFontPadding = false)
    }
}
