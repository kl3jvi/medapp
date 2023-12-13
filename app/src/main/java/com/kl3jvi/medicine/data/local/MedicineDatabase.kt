package com.kl3jvi.medicine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kl3jvi.medicine.data.local.entities.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}