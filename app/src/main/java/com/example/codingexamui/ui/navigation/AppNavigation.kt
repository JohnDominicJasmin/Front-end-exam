package com.example.codingexamui.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codingexamui.presentation.home.ui.screens.HomePageScreen
import com.example.codingexamui.presentation.login.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable(route = "Login"){
            LoginScreen(navController = navController)

        }
        composable(route = "home"){
            HomePageScreen()
        }
    }
}