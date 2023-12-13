package com.kl3jvi.medicine.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.medicine.domain.repositories.MedicineRepository
import com.kl3jvi.medicine.ui.models.HealthDataUiModel
import com.kl3jvi.medicine.utils.Result
import com.kl3jvi.medicine.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {
    val healthData = repository.getHealthData()
        .asResult()
        .map {
            when (it) {
                is Result.Error -> MedicinesUiState.Error(it.exception.message ?: "Unknown error")
                Result.Loading -> MedicinesUiState.Loading
                is Result.Success -> MedicinesUiState.Success(it.data)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            MedicinesUiState.Loading
        )

    fun getHealthDataByName(medicine: String): Flow<HealthDataUiModel> {
        Log.d("MedicineViewModel", "getHealthDataByName: $medicine")
        return repository.getHealthDataByName(name = medicine)
    }
}

sealed interface MedicinesUiState {
    data object Loading : MedicinesUiState
    data class Success(val medicines: Map<String, List<HealthDataUiModel>>) : MedicinesUiState
    data class Error(val message: String) : MedicinesUiState
}