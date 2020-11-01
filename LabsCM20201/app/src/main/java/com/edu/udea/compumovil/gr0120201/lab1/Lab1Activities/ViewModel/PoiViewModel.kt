package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Config.PoiDataBase
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Respositories.PoiRepository
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PoiViewModel(application: Application): AndroidViewModel(application) {


    val readAllData: LiveData<List<Poi>>
    private val repository: PoiRepository

    init {
        val poiDao = PoiDataBase.getDatabase(application).poiDao()
        repository = PoiRepository(poiDao)
        readAllData = repository.readAllData
    }

    fun addPoi(poi: Poi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPoi(poi)
        }
    }

    fun updatePoi(poi: Poi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePoi(poi)
        }
    }

    fun deletePoi(poi: Poi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePoi(poi)
        }
    }
}