package at.sw21_tug.team_25.expirydates.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExpItem::class], version = 1, exportSchema = false)
public abstract class ExpItemDatabase: RoomDatabase() {

    abstract fun expItemDao(): ExpItemDao

    companion object {
        @Volatile
        private var INSTANCE: ExpItemDatabase? = null

        fun getDatabase(context: Context): ExpItemDatabase {
            val tempInstance = this.INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpItemDatabase::class.java,
                    "item_database"
                ).build()
                this.INSTANCE = instance
                return instance
            }
        }
    }

}