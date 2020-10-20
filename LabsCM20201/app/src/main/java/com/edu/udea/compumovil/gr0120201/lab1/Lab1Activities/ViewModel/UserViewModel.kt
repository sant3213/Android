package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Config.UserDatabase
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Respositories.UserRepository
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    var userGotted = MutableLiveData<User>()

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

    fun getUser(userName: String): User {
        var user = User()
        viewModelScope. launch(Dispatchers.IO) {
             user = repository.getUser(userName)

            userGotted.postValue(user)
        }
        return user
    }
}