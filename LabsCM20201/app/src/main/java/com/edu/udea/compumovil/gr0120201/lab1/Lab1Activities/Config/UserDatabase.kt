package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos.UserDao
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}