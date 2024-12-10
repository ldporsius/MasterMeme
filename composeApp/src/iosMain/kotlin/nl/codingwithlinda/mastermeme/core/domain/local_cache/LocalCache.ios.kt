package nl.codingwithlinda.mastermeme.core.domain.local_cache

import local_storage.StorageInteractor

actual class LocalCache(

) {
    actual fun <T> storageInteractor(): StorageInteractor<T> {
        TODO("Not yet implemented")
    }
}