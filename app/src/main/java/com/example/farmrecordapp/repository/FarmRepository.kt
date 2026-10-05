package com.example.farmrecordapp.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.farmrecordapp.model.Animal
import com.example.farmrecordapp.model.ExpenseRecord
import com.example.farmrecordapp.model.FeedRecord
import com.example.farmrecordapp.model.HealthRecord
import com.example.farmrecordapp.model.SaleRecord

object FarmRepository {

    val animals = mutableStateListOf(
        Animal(
            id = "1",
            name = "Sow 01",
            title = "Sow (Breeding)",
            type = "Pig",
            age = "1 year 6 months",
            gender = "Female",
            breed = "Large White",
            iconEmoji = "🐖",
            dob = "15 Mar 2025"
        ),
        Animal(
            id = "2",
            name = "Piglet 01",
            title = "Piglet",
            type = "Pig",
            age = "2 weeks",
            gender = "Male",
            breed = "Large White",
            iconEmoji = "🐖",
            dob = "01 Sep 2026"
        ),
        Animal(
            id = "3",
            name = "Goat 01",
            title = "Boer Goat",
            type = "Goat",
            age = "8 months",
            gender = "Male",
            breed = "Boer",
            iconEmoji = "🐐",
            dob = "10 Jan 2026"
        ),
        Animal(
            id = "4",
            name = "Cow 01",
            title = "Dairy Cow",
            type = "Cow",
            age = "3 years",
            gender = "Female",
            breed = "Holstein",
            iconEmoji = "🐄",
            dob = "12 Aug 2023"
        ),
        Animal(
            id = "5",
            name = "Chicken 01",
            title = "Layer Chicken",
            type = "Chicken",
            age = "6 months",
            gender = "Female",
            breed = "Rhode Island",
            iconEmoji = "🐔",
            dob = "10 Mar 2026"
        ),
        Animal(
            id = "6",
            name = "Rooster 01",
            title = "Rooster",
            type = "Chicken",
            age = "1 year",
            gender = "Male",
            breed = "Leghorn",
            iconEmoji = "🐓",
            dob = "15 Sep 2025"
        )
    )

    val healthRecords = mutableStateListOf(
        HealthRecord(
            id = "1",
            animalId = "1",
            animalName = "Sow",
            type = "Vaccination",
            medication = "FMD Vaccine",
            date = "01 Aug 2026"
        ),
        HealthRecord(
            id = "2",
            animalId = "2",
            animalName = "Piglet",
            type = "Vaccination",
            medication = "Anthrax Vaccine",
            date = "20 Jun 2026"
        ),
        HealthRecord(
            id = "3",
            animalId = "3",
            animalName = "Goat",
            type = "Deworming",
            medication = "Deworming",
            date = "15 Sep 2026"
        ),
        HealthRecord(
            id = "4",
            animalId = "5",
            animalName = "Chicken",
            type = "Vaccination",
            medication = "Rabies Vaccine",
            date = "10 May 2026"
        ),
        HealthRecord(
            id = "5",
            animalId = "1",
            animalName = "Sow",
            type = "Treatment",
            medication = "Treatment",
            date = "12 Jul 2026"
        )
    )

    val feedRecords = mutableStateListOf(
        FeedRecord(
            id = "1",
            feedType = "Maize Bran",
            quantity = "10 kg",
            cost = "MK 12,000",
            date = "10 Sep 2026"
        ),
        FeedRecord(
            id = "2",
            feedType = "Pig Meal",
            quantity = "5 kg",
            cost = "MK 8,500",
            date = "05 Sep 2026"
        ),
        FeedRecord(
            id = "3",
            feedType = "Hay",
            quantity = "20 kg",
            cost = "MK 6,000",
            date = "01 Sep 2026"
        ),
        FeedRecord(
            id = "4",
            feedType = "Concentrate",
            quantity = "8 kg",
            cost = "MK 10,000",
            date = "28 Aug 2026"
        )
    )

    val expenseRecords = mutableStateListOf(
        ExpenseRecord(
            id = "1",
            category = "Veterinary",
            amount = "MK 15,000",
            date = "12 Sep 2026"
        ),
        ExpenseRecord(
            id = "2",
            category = "Feed Purchase",
            amount = "MK 8,000",
            date = "05 Sep 2026"
        ),
        ExpenseRecord(
            id = "3",
            category = "Equipment",
            amount = "MK 25,000",
            date = "20 Aug 2026"
        ),
        ExpenseRecord(
            id = "4",
            category = "Transport",
            amount = "MK 12,000",
            date = "10 Aug 2026"
        )
    )

    val saleRecords = mutableStateListOf(
        SaleRecord(
            id = "1",
            item = "Piglets (3)",
            category = "Live Animals",
            amount = "MK 45,000",
            date = "20 Sep 2026"
        ),
        SaleRecord(
            id = "2",
            item = "Chicken (10)",
            category = "Live Animals",
            amount = "MK 25,000",
            date = "15 Sep 2026"
        ),
        SaleRecord(
            id = "3",
            item = "Eggs (crate)",
            category = "Products",
            amount = "MK 12,000",
            date = "10 Sep 2026"
        ),
        SaleRecord(
            id = "4",
            item = "Goat (1)",
            category = "Live Animals",
            amount = "MK 35,000",
            date = "05 Sep 2026"
        )
    )

    fun addAnimal(animal: Animal) {
        animals.add(animal)
    }

    fun addHealthRecord(record: HealthRecord) {
        healthRecords.add(0, record)
    }

    fun addFeedRecord(record: FeedRecord) {
        feedRecords.add(0, record)
    }

    fun addExpenseRecord(record: ExpenseRecord) {
        expenseRecords.add(0, record)
    }

    fun addSaleRecord(record: SaleRecord) {
        saleRecords.add(0, record)
    }
}
