package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val user_name: String,
    val email: String,
    val password: String
)