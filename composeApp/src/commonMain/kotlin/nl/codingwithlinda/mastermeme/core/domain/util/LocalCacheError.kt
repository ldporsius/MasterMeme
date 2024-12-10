package nl.codingwithlinda.mastermeme.core.domain.util

sealed interface LocalCacheError: Error{
    data object MemeNotFound: LocalCacheError
    data object UnknownError: LocalCacheError
}