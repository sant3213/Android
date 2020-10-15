package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Config.UserDatabase
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Respositories.UserRepository
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}