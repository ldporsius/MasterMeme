package nl.codingwithlinda.mastermeme.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    object mainGraph: Route

    @Serializable
    data object MemeList: Route

    @Serializable
    data class MemeCreator(
        val memeId: String
    ): Route

    @Serializable
    data class MemeSave(
        val memeId: String,
    ): Route

}