package com.kl3jvi.medicine.domain.mapper

import com.kl3jvi.medicine.data.local.entities.MedicineEntity
import com.kl3jvi.medicine.data.remote.models.HealthProblem
import com.kl3jvi.medicine.ui.models.HealthDataUiModel


fun Map<String, List<HealthProblem>>.toHealthDataEntities(): List<MedicineEntity> {
    val healthDataEntities = mutableListOf<MedicineEntity>()
    this.forEach { (problemName, diseases) ->
        diseases.forEach { disease ->
            disease.medications?.forEach { medication ->
                healthDataEntities.add(
                    MedicineEntity(
                        name = medication.name.orEmpty(),
                        dose = medication.dose.orEmpty(),
                        strength = medication.strength.orEmpty(),
                        problemName = problemName
                    )
                )
            }
        }
    }
    return healthDataEntities
}

fun MedicineEntity.toUiModel(): HealthDataUiModel {
    return HealthDataUiModel(
        name = this.name,
        dose = this.dose,
        strength = this.strength,
        problemName = this.problemName
    )
}

