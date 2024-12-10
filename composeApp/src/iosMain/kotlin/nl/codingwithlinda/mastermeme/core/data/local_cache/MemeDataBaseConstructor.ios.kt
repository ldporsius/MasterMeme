package nl.codingwithlinda.mastermeme.core.data.local_cache

import androidx.room.RoomDatabaseConstructor
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase

//@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object MemeDatabaseConstructor : RoomDatabaseConstructor<MemeDatabase> {
    actual override fun initialize(): MemeDatabase {
        TODO("Not yet implemented")
    }
}