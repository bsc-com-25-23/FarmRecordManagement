package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.model.HealthRecord
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.FarmDropdownSelect
import com.example.farmrecordapp.ui.components.FarmInputField
import com.example.farmrecordapp.ui.components.FarmTopBar
import com.example.farmrecordapp.ui.components.PrimaryGreen

@Composable
fun AddHealthRecordScreen(
    onBack: () -> Unit
) {
    var selectedType by remember { mutableStateOf("Vaccination") }
    var selectedAnimal by remember { mutableStateOf(FarmRepository.animals.firstOrNull()?.name ?: "Sow 01") }
    var date by remember { mutableStateOf("") }
    var medication by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val animalNames = FarmRepository.animals.map { it.name }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        FarmTopBar(
            title = "Add Health Record",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // TYPE SELECTOR BUTTONS
            Text(
                text = "Type",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                listOf(
                    Triple("Vaccination", "💉", Color(0xFFE8F1FF)),
                    Triple("Deworming", "🩺", Color(0xFFE5F5E4)),
                    Triple("Treatment", "💊", Color(0xFFFFEAEA))
                ).forEach { (type, emoji, bg) ->
                    val isSelected = type == selectedType
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 6.dp)
                            .background(
                                color = if (isSelected) bg else Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedType = type }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = emoji, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = type,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) PrimaryGreen else Color(0xFF555555)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            FarmDropdownSelect(
                label = "Animal",
                selectedOption = selectedAnimal,
                options = animalNames,
                onOptionSelected = { selectedAnimal = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Date",
                value = date,
                onValueChange = { date = it },
                placeholder = "📅 Select date"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Medication / Vaccine",
                value = medication,
                onValueChange = { medication = it },
                placeholder = "e.g. FMD Vaccine"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Notes (optional)",
                value = notes,
                onValueChange = { notes = it },
                placeholder = "e.g. Next due in 6 months",
                singleLine = false,
                modifier = Modifier.height(90.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val record = HealthRecord(
                        id = System.currentTimeMillis().toString(),
                        animalId = "1",
                        animalName = selectedAnimal,
                        type = selectedType,
                        medication = medication.ifBlank { selectedType },
                        date = date.ifBlank { "25 Sep 2026" },
                        notes = notes
                    )
                    FarmRepository.addHealthRecord(record)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Save Record",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
