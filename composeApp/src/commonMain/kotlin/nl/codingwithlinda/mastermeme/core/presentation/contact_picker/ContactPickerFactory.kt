package nl.codingwithlinda.mastermeme.core.presentation.contact_picker

import androidx.compose.runtime.Composable

expect class ContactPickerFactory {

    @Composable
    fun create(): ContactPicker

}