package local_storage.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import local_storage.room.model.MemeEntity


@Database(
    entities = [MemeEntity::class],
    version = 1
)
abstract class MemeDatabase: RoomDatabase() {
    abstract fun memeDao(): MemeDao
}