package com.kl3jvi.medicine.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey
    val name: String,
    val dose: String,
    val strength: String,
    val problemName: String
)