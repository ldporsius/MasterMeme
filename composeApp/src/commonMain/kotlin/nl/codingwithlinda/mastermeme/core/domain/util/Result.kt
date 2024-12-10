package nl.codingwithlinda.mastermeme.core.domain.util

sealed interface Result<T, E: MemeError> {
    data class Success<T, E: MemeError>(val data: T) : Result<T, E>
    data class Error<T, E: MemeError>(val error: E) : Result<T, E>
}