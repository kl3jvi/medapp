package com.kl3jvi.medicine.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kl3jvi.medicine.ui.components.MedicineDetailCard
import com.kl3jvi.medicine.ui.viewmodels.MedicineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(
    medicine: String,
    navController: NavController,
    medicineViewModel: MedicineViewModel = hiltViewModel()
) {
    val currentMedicine =
        medicineViewModel.getHealthDataByName(medicine).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Medicine Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        currentMedicine.value?.let {
            MedicineDetailCard(medicine = it, modifier = Modifier.padding(paddingValues))
        }
    }
}
