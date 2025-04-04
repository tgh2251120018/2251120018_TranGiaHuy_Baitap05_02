package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.uthsmarttasks.ui.screens.*
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        firebaseAuth = FirebaseAuth.getInstance()

        setContent {
            UTHSmartTasksTheme {
                val navController = rememberNavController()
                MainNavigation(navController, firebaseAuth)
            }
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController, firebaseAuth: FirebaseAuth) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController, firebaseAuth) }
            composable("profile") { ProfileScreen(navController, firebaseAuth) }
            composable("list") { ListScreen(
                onItemClick = { task -> task?.let { navController.navigate("detail_list/${it.id}") } },
                onEmptyList = { navController.navigate("list_error") }
            )}
            composable("detail_list/{taskId}") { backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                taskId?.let { DetailListScreen(navController, it) }
            }
            composable("list_error") { ListErrorScreen(navController) }
        }
    }
}
