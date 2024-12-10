package nl.codingwithlinda.mastermeme.core.domain.local_cache

import local_storage.StorageInteractor
import local_storage.room.RoomStorageInteractor
import local_storage.room.database.DatabaseFactory
import local_storage.room.database.MemeDatabase
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme

actual class LocalCache(
    private val databaseFactory: DatabaseFactory
) {
    actual fun storageInteractor(): StorageInteractor<Meme> {

        val db: MemeDatabase =  databaseFactory.create().build()

        val storageInteractor = RoomStorageInteractor(db)
        return storageInteractor

    }
}