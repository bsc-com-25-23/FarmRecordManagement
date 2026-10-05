package com.example.farmrecordapp.model

data class Animal(
    val id: String,
    val name: String,
    val type: String, // Pig, Goat, Cow, Chicken
    val title: String, // e.g. Sow (Breeding)
    val age: String,
    val gender: String,
    val breed: String,
    val iconEmoji: String,
    val dob: String = "15 Jan 2025"
)

data class HealthRecord(
    val id: String,
    val animalId: String,
    val animalName: String,
    val type: String, // Vaccination, Deworming, Treatment
    val medication: String,
    val date: String,
    val notes: String = ""
)

data class FeedRecord(
    val id: String,
    val feedType: String,
    val quantity: String,
    val cost: String,
    val date: String,
    val notes: String = ""
)

data class ExpenseRecord(
    val id: String,
    val category: String, // Veterinary, Feed Purchase, Equipment, Transport
    val amount: String,
    val date: String
)

data class SaleRecord(
    val id: String,
    val item: String,
    val category: String, // Live Animals, Products
    val amount: String,
    val date: String
)
