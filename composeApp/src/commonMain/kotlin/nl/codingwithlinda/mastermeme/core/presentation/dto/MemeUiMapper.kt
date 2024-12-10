package nl.codingwithlinda.mastermeme.core.presentation.dto

import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

fun Meme.toUi(imageUi: MemeImageUi): MemeUi{
    return MemeUi(
       id = this.id,
        name = this.name,
        image = imageUi,
        dateCreated = this.dateCreated
    )

}