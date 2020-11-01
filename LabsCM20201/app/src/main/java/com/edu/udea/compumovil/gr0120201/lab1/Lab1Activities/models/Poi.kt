package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "poi")
data class Poi(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val location: String,
    val imageName: String
) : Parcelable