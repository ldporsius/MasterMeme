package nl.codingwithlinda.mastermeme.core.domain.local_cache

import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor

actual class LocalCache(

) {
    actual fun <T> storageInteractor(): StorageInteractor<T> {
        TODO("Not yet implemented")
    }
}