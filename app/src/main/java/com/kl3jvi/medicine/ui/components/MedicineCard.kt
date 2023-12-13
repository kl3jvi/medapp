package com.kl3jvi.medicine.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kl3jvi.medicine.ui.models.HealthDataUiModel

@Composable
fun MedicineCard(
    medicine: HealthDataUiModel,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Medication,
                    contentDescription = "Medicine Icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = medicine.name,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Dose: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = medicine.dose,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(600)

                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Strength: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = medicine.strength,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MedicineCardPreview() {
    Column {
        MedicineCard(
            medicine = HealthDataUiModel(
                name = "Paracetamol",
                dose = "1",
                strength = "500mg",
                problemName = "Fever"
            )
        )
        MedicineCard(
            medicine = HealthDataUiModel(
                name = "Paracetamol",
                dose = "1",
                strength = "500mg",
                problemName = "Fever"
            )
        )
        MedicineCard(
            medicine = HealthDataUiModel(
                name = "Paracetamol",
                dose = "1",
                strength = "500mg",
                problemName = "Fever"
            )
        )
    }
}
