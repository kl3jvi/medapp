package com.kl3jvi.medicine.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kl3jvi.medicine.data.local.entities.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<MedicineEntity>)

    @Query("SELECT * FROM medicines")
    fun getMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE name = :name LIMIT 1")
    fun getMedicineByName(name: String): Flow<MedicineEntity>
}