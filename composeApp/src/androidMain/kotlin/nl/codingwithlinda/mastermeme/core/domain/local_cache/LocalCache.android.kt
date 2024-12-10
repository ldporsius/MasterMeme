package nl.codingwithlinda.mastermeme.core.domain.local_cache

import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.RoomStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_cache.DatabaseFactory
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme

actual class LocalCache(
    private val databaseFactory: DatabaseFactory
) {
    actual fun storageInteractor(): StorageInteractor<Meme> {

        val db: MemeDatabase =  databaseFactory.create()
            .fallbackToDestructiveMigration(dropAllTables = false)
            .build()

        val storageInteractor = RoomStorageInteractor(db)
        return storageInteractor

    }
}