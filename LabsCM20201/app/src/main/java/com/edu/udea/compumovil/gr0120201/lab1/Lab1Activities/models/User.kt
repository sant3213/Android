package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int,
    val email: String,
    val password: String
)