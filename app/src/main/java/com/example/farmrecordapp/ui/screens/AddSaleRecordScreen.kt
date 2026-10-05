package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.model.SaleRecord
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.FarmDropdownSelect
import com.example.farmrecordapp.ui.components.FarmInputField
import com.example.farmrecordapp.ui.components.FarmTopBar
import com.example.farmrecordapp.ui.components.PrimaryGreen

@Composable
fun AddSaleRecordScreen(
    onBack: () -> Unit
) {
    var item by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Live Animals") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    val categories = listOf("Live Animals", "Products")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        FarmTopBar(
            title = "Add Sale Record",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            FarmInputField(
                label = "Item Sold",
                value = item,
                onValueChange = { item = it },
                placeholder = "e.g. Piglets (3)"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmDropdownSelect(
                label = "Category",
                selectedOption = category,
                options = categories,
                onOptionSelected = { category = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Amount (MK)",
                value = amount,
                onValueChange = { amount = it },
                placeholder = "e.g. 45000"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Date",
                value = date,
                onValueChange = { date = it },
                placeholder = "📅 Select date"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val record = SaleRecord(
                        id = System.currentTimeMillis().toString(),
                        item = item.ifBlank { "Piglets (3)" },
                        category = category,
                        amount = if (amount.isNotBlank()) "MK $amount" else "MK 45,000",
                        date = date.ifBlank { "25 Sep 2026" }
                    )
                    FarmRepository.addSaleRecord(record)
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
