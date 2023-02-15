package com.example.scaffold_intro

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold_intro.ui.theme.Scaffold_IntroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold_IntroTheme {
                ScaffoldApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home" ){
        composable(route = "Home"){ MainScreen(navController)}
        composable(route = "Info"){ InfoScreen(navController)}
        composable(route = "Settings"){ SettingScreen(navController)}
    }
}

@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title)},
     /*   navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        }*/
        actions = {
            IconButton(
                onClick = { expanded = !expanded}
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded }) {
                DropdownMenuItem(onClick = { navController.navigate("info")}) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings")}) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar (
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }

        }
            )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController) },
        content = { Text(text = "HomeScreen Content") },
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController)},
        content = { Text(text = "CONTENT FOR INFORMATION")}
    )

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController)},
        content = { Text(text = "CONTENTS FOR SETTINGS")}
    )

}