package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poi")
data class Poi(
    @PrimaryKey
    val title: String,
    val description: String,
    val location: String
)