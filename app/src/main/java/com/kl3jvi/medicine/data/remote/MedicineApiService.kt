package com.kl3jvi.medicine.data.remote

import com.kl3jvi.medicine.data.remote.models.HealthDataResponse
import retrofit2.http.GET

interface MedicineApiService {
    @GET("v3/56eec2b4-cde7-4328-95f6-c05dd73bf5cf")
    suspend fun getHealthData(): HealthDataResponse
}