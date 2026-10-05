package com.example.farmrecordapp.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.farmrecordapp.model.Animal
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.screens.AddAnimalScreen
import com.example.farmrecordapp.ui.screens.AddExpenseRecordScreen
import com.example.farmrecordapp.ui.screens.AddFeedRecordScreen
import com.example.farmrecordapp.ui.screens.AddHealthRecordScreen
import com.example.farmrecordapp.ui.screens.AddSaleRecordScreen
import com.example.farmrecordapp.ui.screens.AnimalDetailsScreen
import com.example.farmrecordapp.ui.screens.AnimalRecordsScreen
import com.example.farmrecordapp.ui.screens.ExpenseRecordsScreen
import com.example.farmrecordapp.ui.screens.FeedRecordsScreen
import com.example.farmrecordapp.ui.screens.HealthRecordsScreen
import com.example.farmrecordapp.ui.screens.HomeScreen
import com.example.farmrecordapp.ui.screens.ReportsScreen
import com.example.farmrecordapp.ui.screens.SalesRecordsScreen
import com.example.farmrecordapp.ui.screens.SettingsScreen
import com.example.farmrecordapp.ui.screens.WelcomeScreen

sealed class Screen {
    object Welcome : Screen()
    object Home : Screen()
    object Animals : Screen()
    object AddAnimal : Screen()
    data class AnimalDetails(val animal: Animal) : Screen()
    object Health : Screen()
    object AddHealthRecord : Screen()
    object Feed : Screen()
    object AddFeedRecord : Screen()
    object Expenses : Screen()
    object AddExpenseRecord : Screen()
    object Sales : Screen()
    object AddSaleRecord : Screen()
    object Reports : Screen()
    object Settings : Screen()
}

@Composable
fun FarmRecordApp() {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Welcome) }
    val currentScreen = backStack.lastOrNull() ?: Screen.Welcome

    fun navigateTo(screen: Screen) {
        if (backStack.lastOrNull() != screen) {
            backStack.add(screen)
        }
    }

    fun navigateBack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    // Handle System Back Button / Gestures
    BackHandler(enabled = backStack.size > 1) {
        navigateBack()
    }

    val handleTabSelected: (BottomTab) -> Unit = { tab ->
        val targetScreen = when (tab) {
            BottomTab.HOME -> Screen.Home
            BottomTab.ANIMALS -> Screen.Animals
            BottomTab.HEALTH -> Screen.Health
            BottomTab.FEED -> Screen.Feed
            BottomTab.MORE -> Screen.Settings
        }
        if (currentScreen != targetScreen) {
            navigateTo(targetScreen)
        }
    }

    when (val screen = currentScreen) {
        is Screen.Welcome -> {
            WelcomeScreen(
                onGetStarted = {
                    backStack.clear()
                    backStack.add(Screen.Home)
                }
            )
        }

        is Screen.Home -> {
            HomeScreen(
                onNavigateToAnimals = { navigateTo(Screen.Animals) },
                onNavigateToHealth = { navigateTo(Screen.Health) },
                onNavigateToFeed = { navigateTo(Screen.Feed) },
                onNavigateToExpenses = { navigateTo(Screen.Expenses) },
                onNavigateToSales = { navigateTo(Screen.Sales) },
                onNavigateToReports = { navigateTo(Screen.Reports) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.Animals -> {
            AnimalRecordsScreen(
                onBack = { navigateBack() },
                onAddAnimalClick = { navigateTo(Screen.AddAnimal) },
                onAnimalClick = { animal -> navigateTo(Screen.AnimalDetails(animal)) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.AddAnimal -> {
            AddAnimalScreen(
                onBack = { navigateBack() }
            )
        }

        is Screen.AnimalDetails -> {
            AnimalDetailsScreen(
                animal = screen.animal,
                onBack = { navigateBack() },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.Health -> {
            HealthRecordsScreen(
                onBack = { navigateBack() },
                onAddHealthRecordClick = { navigateTo(Screen.AddHealthRecord) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.AddHealthRecord -> {
            AddHealthRecordScreen(
                onBack = { navigateBack() }
            )
        }

        is Screen.Feed -> {
            FeedRecordsScreen(
                onBack = { navigateBack() },
                onAddFeedRecordClick = { navigateTo(Screen.AddFeedRecord) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.AddFeedRecord -> {
            AddFeedRecordScreen(
                onBack = { navigateBack() }
            )
        }

        is Screen.Expenses -> {
            ExpenseRecordsScreen(
                onBack = { navigateBack() },
                onAddExpenseClick = { navigateTo(Screen.AddExpenseRecord) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.AddExpenseRecord -> {
            AddExpenseRecordScreen(
                onBack = { navigateBack() }
            )
        }

        is Screen.Sales -> {
            SalesRecordsScreen(
                onBack = { navigateBack() },
                onAddSaleClick = { navigateTo(Screen.AddSaleRecord) },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.AddSaleRecord -> {
            AddSaleRecordScreen(
                onBack = { navigateBack() }
            )
        }

        is Screen.Reports -> {
            ReportsScreen(
                onBack = { navigateBack() },
                onTabSelected = handleTabSelected
            )
        }

        is Screen.Settings -> {
            SettingsScreen(
                onBack = { navigateBack() },
                onTabSelected = handleTabSelected,
                onNavigateToExpenses = { navigateTo(Screen.Expenses) },
                onNavigateToSales = { navigateTo(Screen.Sales) },
                onNavigateToReports = { navigateTo(Screen.Reports) }
            )
        }
    }
}
