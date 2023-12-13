package com.kl3jvi.medicine.data.repositories

import com.kl3jvi.medicine.data.local.MedicineDao
import com.kl3jvi.medicine.data.local.entities.MedicineEntity
import com.kl3jvi.medicine.data.remote.MedicineApiClient
import com.kl3jvi.medicine.domain.mapper.toHealthDataEntities
import com.kl3jvi.medicine.domain.mapper.toUiModel
import com.kl3jvi.medicine.domain.repositories.MedicineRepository
import com.kl3jvi.medicine.ui.models.HealthDataUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao,
    private val apiClient: MedicineApiClient
) : MedicineRepository {

    override fun getHealthData() = medicineDao.getMedicines()
        .map { entities ->
            entities.map(MedicineEntity::toUiModel)
                .groupBy(HealthDataUiModel::problemName)
        }.onStart {
            val response = apiClient.getHealthData().problems
            val entities = response.toHealthDataEntities()
            medicineDao.insertMedicines(entities)
        }

    override fun getHealthDataByName(name: String): Flow<HealthDataUiModel> {
        return medicineDao.getMedicineByName(name).map(MedicineEntity::toUiModel)
    }
}