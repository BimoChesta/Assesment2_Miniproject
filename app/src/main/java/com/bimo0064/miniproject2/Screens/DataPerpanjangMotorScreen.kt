package com.bimo0064.miniproject2.Screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.bimo0064.miniproject2.model.DataPerpanjangMotor

@Composable
fun DataPerpanjangMotorScreen(navController: NavHostController) {
    val data = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<DataPerpanjangMotor>("submittedData")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Data Perpanjangan Motor", style = MaterialTheme.typography.headlineSmall)

        if (data != null) {
            Text("Nama: ${data.name}")
            Text("Motor: ${data.room}")
            Text("Hari: ${data.month}")

            Spacer(modifier = Modifier.height(8.dp))

            if (!data.imageUri.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(Uri.parse(data.imageUri)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Kembali")
        }
    }
}