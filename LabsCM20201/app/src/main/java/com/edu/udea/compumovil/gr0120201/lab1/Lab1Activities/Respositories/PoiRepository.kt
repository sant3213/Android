package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Respositories

import androidx.lifecycle.LiveData
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos.PoiDao
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi

class PoiRepository(private val poiDao: PoiDao) {
    val readAllData: LiveData<List<Poi>> = poiDao.readAllData()

    suspend fun addUser(poi: Poi){
        poiDao.addUser(poi)
    }

}