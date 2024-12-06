package nl.codingwithlinda.mastermeme.core.presentation.contact_picker

import androidx.compose.runtime.Composable

expect class ContactPicker {

    @Composable
    fun registerPicker(onPickContact: (String?) -> Unit)

    fun pickContact()
}