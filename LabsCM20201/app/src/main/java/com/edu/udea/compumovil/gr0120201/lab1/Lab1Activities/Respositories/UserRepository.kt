package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.Respositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos.UserDao
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun getUser(userName: String): User {
        return userDao.findByEmail(userName)
    }
}