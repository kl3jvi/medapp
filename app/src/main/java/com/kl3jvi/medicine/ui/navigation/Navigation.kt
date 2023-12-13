package com.kl3jvi.medicine.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kl3jvi.medicine.ui.screen.LoginScreen
import com.kl3jvi.medicine.ui.screen.MainScreen
import com.kl3jvi.medicine.ui.screen.MedicineDetailScreen


sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Main : Screen("main/{username}") {
        fun createRoute(username: String) = "main/$username"
    }

    data object MedicineDetail : Screen("medicineDetail/{medicineName}") {
        fun createRoute(medicineName: String) = "medicineDetail/$medicineName"
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Main.route) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Discrete User"
            MainScreen(navController = navController, username = username)
        }
        composable(Screen.MedicineDetail.route) { backStackEntry ->
            val medicine = backStackEntry.arguments
                ?.getString("medicineName")
                ?: throw IllegalStateException("medicineId shouldn't be null")

            Log.e("AppNavigator", "AppNavigator: $medicine")
            MedicineDetailScreen(medicine = medicine, navController = navController)
        }
    }
}