package com.example.mytv

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.Search
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "discover") {
        composable("discover") { DiscoverScreen(navController = navController) }
        composable("activity") { ActivityFriends(navController = navController) }
        composable("profile")  {MyPageContent(navController = navController)}
        composable("nearby")  {NearByScreen(navController = navController)}
        composable("search")  {Search(navController = navController)}

    }
}
