package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.model.FeedRecord
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.FarmDropdownSelect
import com.example.farmrecordapp.ui.components.FarmInputField
import com.example.farmrecordapp.ui.components.FarmTopBar
import com.example.farmrecordapp.ui.components.PrimaryGreen

@Composable
fun AddFeedRecordScreen(
    onBack: () -> Unit
) {
    var feedType by remember { mutableStateOf("Maize Bran") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("kg") }
    var cost by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val feedTypes = listOf("Maize Bran", "Pig Meal", "Hay", "Concentrate")
    val units = listOf("kg", "g", "bags", "tonnes")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        FarmTopBar(
            title = "Add Feed Record",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            FarmDropdownSelect(
                label = "Feed Type",
                selectedOption = feedType,
                options = feedTypes,
                onOptionSelected = { feedType = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                FarmInputField(
                    label = "Quantity",
                    value = quantity,
                    onValueChange = { quantity = it },
                    placeholder = "e.g. 10",
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                FarmDropdownSelect(
                    label = "Unit",
                    selectedOption = unit,
                    options = units,
                    onOptionSelected = { unit = it },
                    modifier = Modifier.width(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Cost (MK)",
                value = cost,
                onValueChange = { cost = it },
                placeholder = "e.g. 12000"
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
                label = "Notes (optional)",
                value = notes,
                onValueChange = { notes = it },
                placeholder = "e.g. Supplier or remarks",
                singleLine = false,
                modifier = Modifier.height(90.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val record = FeedRecord(
                        id = System.currentTimeMillis().toString(),
                        feedType = feedType,
                        quantity = if (quantity.isNotBlank()) "$quantity $unit" else "10 kg",
                        cost = if (cost.isNotBlank()) "MK $cost" else "MK 12,000",
                        date = date.ifBlank { "25 Sep 2026" },
                        notes = notes
                    )
                    FarmRepository.addFeedRecord(record)
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
