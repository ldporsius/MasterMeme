package local_storage.room.database

import androidx.room.RoomDatabaseConstructor


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MemeDatabaseConstructor: RoomDatabaseConstructor<MemeDatabase> {
    override fun initialize(): MemeDatabase
}