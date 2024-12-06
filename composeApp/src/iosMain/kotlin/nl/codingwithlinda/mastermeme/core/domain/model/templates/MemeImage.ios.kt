package nl.codingwithlinda.mastermeme.core.domain.model.templates

actual class MemeImageImpl {
    actual val image: MemeImage
        get() = MemeImage.ResourceImage("id", "image")
}