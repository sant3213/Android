package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi

@Dao
interface PoiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: Poi)

    @Query("SELECT * FROM poi ORDER BY title ASC")
    fun readAllData(): LiveData<List<Poi>>

}