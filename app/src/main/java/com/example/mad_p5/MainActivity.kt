package com.example.mad_p5

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_p5.screen.LoginForm
import com.example.mad_p5.screen.RegistrationForm
import com.example.mad_p5.ui.theme.MAD_P5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAD_P5Theme {
                AppNavigation(context = this)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun AppNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginForm(context,
                navController = navController,
            )
        }
        composable("registration") {
            RegistrationForm(context,
                navController = navController,
            )
        }
    }
}

fun navigateToRegistrationScreen(navController: NavController) {
    navController.navigate("registration") {
        popUpTo("login") { inclusive = true }
    }
}

fun navigateToLoginScreen(navController: NavController) {
    navController.navigate("login") {
        popUpTo("registration") { inclusive = true }
    }
}

