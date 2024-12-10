package nl.codingwithlinda.mastermeme.core.domain.local_cache

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import local_storage.StorageInteractor
import local_storage.room.RoomStorageInteractor
import local_storage.room.database.MemeDatabase
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme

actual class LocalCache(
    val context: Context
) {
    actual fun storageInteractor(): StorageInteractor<Meme> {
        val dbFile = context.getDatabasePath("meme_database")
        val db: MemeDatabase = Room.databaseBuilder<MemeDatabase>(
            context,
            MemeDatabase::class.java,
            name = dbFile.absolutePath

        ).setDriver(BundledSQLiteDriver())
            .build()

        val storageInteractor = RoomStorageInteractor(db)
        return storageInteractor

    }
}