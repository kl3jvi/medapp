package com.kl3jvi.medicine.domain.repositories

import com.kl3jvi.medicine.ui.models.HealthDataUiModel
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    fun getHealthData(): Flow<Map<String, List<HealthDataUiModel>>>
    fun getHealthDataByName(name: String): Flow<HealthDataUiModel>
}