package local_storage.room.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import local_storage.room.model.MemeEntity
import local_storage.room.model.MemeTextEntity


@Database(
    entities = [MemeEntity::class, MemeTextEntity::class],
    version = 1
)
@ConstructedBy(MemeDatabaseConstructor::class)
abstract class MemeDatabase: RoomDatabase() {
    abstract fun memeDao(): MemeDao
    abstract fun memeTextDao(): MemeTextDao

    companion object {
        const val DATABASE_NAME = "nl.codingwithlinda.meme.db"
    }

}