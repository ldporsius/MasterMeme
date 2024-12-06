package nl.codingwithlinda.mastermeme.core.domain.model.templates

interface MemeImage{
   val ref: Int
}
data class ImageReference(override val ref: Int): MemeImage
