package com.openclassrooms.realestatemanager.database

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.realestatemanager.database.dao.ItemDao
import com.openclassrooms.realestatemanager.database.dao.PlaceDao
import com.openclassrooms.realestatemanager.models.Item
import com.openclassrooms.realestatemanager.models.Place

@Database(entities = [Item::class, Place::class], version = 1, exportSchema = false)
abstract class SaveMyPlaceDatabase : RoomDatabase() {

    // DAO
    abstract fun itemDao(): ItemDao

    abstract fun placeDao(): PlaceDao

    // SINGLETON
    object DatabaseProvider {

        private var instance: SaveMyPlaceDatabase? = null

        // instance
        fun getInstance(context: Context): SaveMyPlaceDatabase? {
            if (instance == null) {
                synchronized(SaveMyPlaceDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            SaveMyPlaceDatabase::class.java, "placeDB").build()
                }
            }
            return instance
        }

        fun prepopulateDatabase(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val contentValues = ContentValues()
                    contentValues.put("id", 1)
                    contentValues.put("placeTitle", "examplePlace")
                    contentValues.put("placePrice", 100000)

                    db.insert("Place", OnConflictStrategy.IGNORE, contentValues)
                }

            }

        }
    }



}