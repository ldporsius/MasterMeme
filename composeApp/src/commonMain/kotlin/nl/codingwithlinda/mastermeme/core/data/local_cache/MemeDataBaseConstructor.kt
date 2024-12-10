package nl.codingwithlinda.mastermeme.core.data.local_cache

import androidx.room.RoomDatabaseConstructor
import local_storage.room.database.MemeDatabase


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MemeDatabaseConstructor: RoomDatabaseConstructor<MemeDatabase> {
    override fun initialize(): MemeDatabase
}