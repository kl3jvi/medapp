package com.kl3jvi.medicine.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthDataUiModel(
    val name: String,
    val dose: String,
    val strength: String,
    val problemName: String
) : Parcelable