package com.bimo0064.miniproject2.Screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.bimo0064.miniproject2.viewmodel.DataPerpanjangMotorViewModel

@Composable
fun DataPerpanjangMotorScreen(
    navController: NavHostController,
    viewModel: DataPerpanjangMotorViewModel = viewModel()
) {
    val dataList by viewModel.dataList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Daftar Perpanjangan Motor", style = MaterialTheme.typography.headlineSmall)

        if (dataList.isEmpty()) {
            Text("Belum ada data.")
        } else {
            dataList.forEachIndexed { index, data ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
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
                                Text("Tidak ada gambar")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { viewModel.removeData(index) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("Hapus", color = Color.White)
                        }
                    }
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
