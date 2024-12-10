package nl.codingwithlinda.mastermeme.core.data.local_storage

import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.mastermeme.core.domain.util.MemeError
import nl.codingwithlinda.mastermeme.core.domain.util.Result

interface StorageInteractor<T> {
    suspend fun create(item: T): Result<T, MemeError>
    suspend fun read(id: String): Result<T, MemeError>
    suspend fun update(item: T): Result<T, MemeError>
    suspend fun delete(id: String): Result<Unit, MemeError>

    fun readAll(): Flow<List<T>>

}