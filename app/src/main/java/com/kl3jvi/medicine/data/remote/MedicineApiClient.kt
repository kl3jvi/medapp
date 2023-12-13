package com.kl3jvi.medicine.data.remote

import javax.inject.Inject

class MedicineApiClient @Inject constructor(
    private val medicineApiService: MedicineApiService
) {
    suspend fun getHealthData() = medicineApiService.getHealthData()
}