package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi

@Dao
interface PoiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPoi(poi: Poi)

    @Query("SELECT * FROM poi ORDER BY title ASC")
    fun readAllData(): LiveData<List<Poi>>

    @Update
    suspend fun updatePoi(poi: Poi)

    @Delete
    suspend fun  deletePoi(title:Poi)

}