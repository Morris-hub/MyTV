package com.example.mytv

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MytvApp () {
    Navigation()
}

@Composable

fun Navigation() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "Login") {
            Login(
                navigateToRegister = { navController.navigate("Register") },
                navigateToMyPageContent = { navController.navigate("MyPageContent") }
            )
        }

            composable(route = "Register") {
                Register(
                    navigateToLogin = { navController.navigate("Login") })

            }
        composable(route = "MyPageContent") {
            MyPageContent()


        }
    }
}