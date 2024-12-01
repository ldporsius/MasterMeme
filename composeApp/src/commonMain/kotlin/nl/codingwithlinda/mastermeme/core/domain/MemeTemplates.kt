package nl.codingwithlinda.mastermeme.core.domain

import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi

fun interface MemeTemplates {
    fun getTemplates(): List<MemeImageUi>
}