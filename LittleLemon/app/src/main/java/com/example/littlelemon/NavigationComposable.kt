package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navController: NavHostController,
    menuDao: MenuItemDao
){
    // 1. Obtenemos el contexto para acceder a SharedPreferences
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // 2. Determinamos el startDestination
    // Supongamos que guardamos un booleano "is_registered" cuando el usuario completa el onboarding
    val isUserDataStored = sharedPreferences.getBoolean("is_registered", false)

    // Si hay datos, vamos a Home; si no, a Onboarding
    val startDestination = if (isUserDataStored) Home.route else Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, menuDao)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}