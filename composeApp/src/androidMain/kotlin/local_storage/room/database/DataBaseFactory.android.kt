package local_storage.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<MemeDatabase> {

        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(MemeDatabase.DATABASE_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}
