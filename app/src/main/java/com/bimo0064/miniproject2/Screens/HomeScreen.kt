package com.bimo0064.miniproject2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bimo0064.miniproject2.R
import com.bimo0064.miniproject2.data.DataStoreManager
import com.bimo0064.miniproject2.model.DataPerpanjangMotor
import com.bimo0064.miniproject2.model.User

@Composable
fun HomeScreen(
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {
    var user by remember { mutableStateOf<User?>(null) }
    val data = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<DataPerpanjangMotor>("submittedData")

    if (data != null) {
        Text("Motor: ${data.name}")
        Text("Tanggal: ${data.month}")
    }


    LaunchedEffect(Unit) {
        user = dataStoreManager.loadUser()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF6F9F9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MenuIcon(R.drawable.aturan, "Aturan", navController, "aturan")
                MenuIcon(R.drawable.perpanjangkost, "Perpanjang Motor", navController, "perpanjangMotor")
                MenuIcon(R.drawable.home, "Informasi Motor", navController, "informasi")
                MenuIcon(R.drawable.perpanjangkost, "Riwayat Perpanjang Motor", navController, "DataPerpanjangMotor")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.Transparent),
                )
                Text(
                    text = "Anda tidak ada riwayat",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
            }
        }
    }
}

@Composable
fun MenuIcon(icon: Int, text: String, navController: NavHostController, route: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { navController.navigate(route) }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}
