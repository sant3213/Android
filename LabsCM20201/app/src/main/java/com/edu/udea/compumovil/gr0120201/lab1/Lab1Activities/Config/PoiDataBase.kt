package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos.PoiDao
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi

@Database(entities = [Poi::class], version = 1, exportSchema = false)
abstract class PoiDataBase : RoomDatabase() {

    abstract fun poiDao(): PoiDao

    companion object {
        @Volatile
        private var INSTANCE: PoiDataBase? = null

        fun getDatabase(context: Context): PoiDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PoiDataBase::class.java,
                    "poi"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}