package nl.codingwithlinda.mastermeme.core.data.local_storage.room.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.model.MemeEntity
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.model.MemeTextEntity
import nl.codingwithlinda.mastermeme.core.data.local_cache.MemeDatabaseConstructor


@Database(
    entities = [MemeEntity::class, MemeTextEntity::class],
    version = 4
)
@ConstructedBy(MemeDatabaseConstructor::class)
abstract class MemeDatabase: RoomDatabase() {
    abstract fun memeDao(): MemeDao
    abstract fun memeTextDao(): MemeTextDao

    companion object {
        const val DATABASE_NAME = "nl.codingwithlinda.meme.db"
    }

}