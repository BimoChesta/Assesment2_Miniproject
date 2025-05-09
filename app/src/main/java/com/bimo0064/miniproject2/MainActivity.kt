package com.bimo0064.miniproject2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bimo0064.miniproject2.Screens.AturanScreen
import com.bimo0064.miniproject2.Screens.DataPerpanjangMotorScreen
import com.bimo0064.miniproject2.Screens.HomeScreen
import com.bimo0064.miniproject2.Screens.InformasiScreen
import com.bimo0064.miniproject2.Screens.PerpanjangMotorScreen
import com.bimo0064.miniproject2.data.DataStoreManager

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppEntryPoint()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppEntryPoint() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    Scaffold(
        topBar = {
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            AppNavGraph(navController = navController, dataStoreManager = dataStoreManager)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController, dataStoreManager: DataStoreManager) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, dataStoreManager)
        }
        composable("informasi") {
            InformasiScreen()
        }
        composable("aturan") {
            AturanScreen()
        }
        composable("perpanjangMotor") {
            PerpanjangMotorScreen(navController)
        }
        composable("DataPerpanjangMotor") {
            DataPerpanjangMotorScreen(navController)
        }
    }
}