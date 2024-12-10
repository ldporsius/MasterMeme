package nl.codingwithlinda.mastermeme.core.domain.local_cache

import local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme

expect class LocalCache {
    fun storageInteractor(): StorageInteractor<Meme>
}