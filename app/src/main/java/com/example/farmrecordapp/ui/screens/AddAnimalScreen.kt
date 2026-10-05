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
import com.example.farmrecordapp.model.Animal
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.DashedPhotoUploadBox
import com.example.farmrecordapp.ui.components.FarmDropdownSelect
import com.example.farmrecordapp.ui.components.FarmInputField
import com.example.farmrecordapp.ui.components.FarmTopBar
import com.example.farmrecordapp.ui.components.PrimaryGreen

@Composable
fun AddAnimalScreen(
    onBack: () -> Unit
) {
    var animalType by remember { mutableStateOf("Pig") }
    var nameId by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Female") }
    var dob by remember { mutableStateOf("") }

    val animalTypes = listOf("Pig", "Goat", "Cow", "Chicken")
    val genders = listOf("Female", "Male")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        FarmTopBar(
            title = "Add Animal",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            DashedPhotoUploadBox()

            Spacer(modifier = Modifier.height(18.dp))

            FarmDropdownSelect(
                label = "Animal Type",
                selectedOption = animalType,
                options = animalTypes,
                onOptionSelected = { animalType = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Name / ID",
                value = nameId,
                onValueChange = { nameId = it },
                placeholder = "e.g. Sow 01"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Breed",
                value = breed,
                onValueChange = { breed = it },
                placeholder = "e.g. Large White"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmDropdownSelect(
                label = "Gender",
                selectedOption = gender,
                options = genders,
                onOptionSelected = { gender = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            FarmInputField(
                label = "Date of Birth",
                value = dob,
                onValueChange = { dob = it },
                placeholder = "📅 Select date"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val emoji = when (animalType) {
                        "Pig" -> "🐖"
                        "Goat" -> "🐐"
                        "Cow" -> "🐄"
                        "Chicken" -> "🐔"
                        else -> "🐖"
                    }
                    val newAnimal = Animal(
                        id = System.currentTimeMillis().toString(),
                        name = nameId.ifBlank { "Animal #${FarmRepository.animals.size + 1}" },
                        title = nameId.ifBlank { "$animalType #${FarmRepository.animals.size + 1}" },
                        type = animalType,
                        age = "Young",
                        gender = gender,
                        breed = breed.ifBlank { "Standard" },
                        iconEmoji = emoji,
                        dob = dob.ifBlank { "15 Sep 2026" }
                    )
                    FarmRepository.addAnimal(newAnimal)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
