package com.kl3jvi.medicine.data.remote.models

import com.google.gson.annotations.SerializedName

data class AssociatedDrug(
    @SerializedName("name")
    val name: String?,
    @SerializedName("dose")
    val dose: String?,
    @SerializedName("strength")
    val strength: String?
)

data class Lab(
    @SerializedName("labTest")
    val labTest: String?,
    @SerializedName("result")
    val result: String?
)

data class HealthProblem(
    @SerializedName("medications")
    val medications: List<AssociatedDrug>?,
    @SerializedName("labs")
    val labs: List<Lab>?
)

data class HealthDataResponse(
    @SerializedName("problems")
    val problems: Map<String, List<HealthProblem>> = emptyMap()
)