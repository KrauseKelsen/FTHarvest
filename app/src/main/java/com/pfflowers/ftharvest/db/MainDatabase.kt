package com.pfflowers.ftharvest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pfflowers.ftharvest.db.dao.LecturaDao
import com.pfflowers.ftharvest.pojos.LecturaCaja

@Database(entities = [LecturaCaja::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun lecturaDao(): LecturaDao

    companion object {
        @Volatile
        private var instance: MainDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MainDatabase::class.java, "Lecturas.db"
        ).build()
    }
}