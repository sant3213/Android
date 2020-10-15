package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.daos


import androidx.lifecycle.LiveData
import androidx.room.*
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY uuid ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_table WHERE uuid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user_table WHERE email LIKE :first")
    fun findByEmail(first: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}