package com.kl3jvi.medicine.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kl3jvi.medicine.ui.components.GreetingScreen
import com.kl3jvi.medicine.ui.components.MedicineCard
import com.kl3jvi.medicine.ui.navigation.Screen
import com.kl3jvi.medicine.ui.viewmodels.MedicineViewModel
import com.kl3jvi.medicine.ui.viewmodels.MedicinesUiState

@Composable
fun MainScreen(
    navController: NavController,
    medicineViewModel: MedicineViewModel = hiltViewModel(),
    username: String
) {
    val medicines = medicineViewModel.healthData.collectAsState()

    when (val state = medicines.value) {
        is MedicinesUiState.Loading -> CircularProgressIndicator()

        is MedicinesUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    GreetingScreen(username = username)
                }
                state.medicines.forEach { (problemName, medicines) ->
                    item {
                        Text(
                            text = problemName,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight(600)
                        )
                    }
                    items(medicines) { medicine ->
                        MedicineCard(medicine = medicine, onClick = {
                            navController.navigate(Screen.MedicineDetail.createRoute(medicine.name))
                        })
                    }
                }
            }
        }

        is MedicinesUiState.Error -> {

        }
    }
}
