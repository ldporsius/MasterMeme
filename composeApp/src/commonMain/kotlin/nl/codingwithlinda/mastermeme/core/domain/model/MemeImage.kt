package nl.codingwithlinda.mastermeme.core.domain.model

interface MemeImage<IMAGE> {
    fun image(uri: String): IMAGE
}