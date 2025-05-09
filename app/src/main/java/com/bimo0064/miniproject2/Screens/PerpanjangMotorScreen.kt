package com.bimo0064.miniproject2.Screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import android.widget.Toast
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.bimo0064.miniproject2.R
import com.bimo0064.miniproject2.model.DataPerpanjangMotor

@Composable
fun PerpanjangMotorScreen(navController: NavHostController) {
    var selectedMotor by remember { mutableStateOf("Motor Beat") }
    var selectedTanggal by remember { mutableStateOf("1") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    var expandedMotor by remember { mutableStateOf(false) }
    var expandedTanggal by remember { mutableStateOf(false) }

    val motorOptions = listOf("Motor Beat", "Motor Scoopy", "Motor Aerox", "Motor Vespa")
    val tanggalOptions = (1..20).map { it.toString() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Scan QR untuk Perpanjang", style = MaterialTheme.typography.titleMedium)
        Image(
            painter = painterResource(id = R.drawable.scan),
            contentDescription = "QR Code",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Fit
        )

        Text("Formulir Perpanjang", style = MaterialTheme.typography.headlineSmall)

        Box {
            OutlinedTextField(
                value = selectedMotor,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Motor") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expandedMotor = true }) {
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                    }
                }
            )

            DropdownMenu(
                expanded = expandedMotor,
                onDismissRequest = { expandedMotor = false }
            ) {
                motorOptions.forEach { motor ->
                    DropdownMenuItem(
                        text = { Text(motor) },
                        onClick = {
                            selectedMotor = motor
                            expandedMotor = false
                        }
                    )
                }
            }
        }

        Box {
            OutlinedTextField(
                value = selectedTanggal,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Tanggal Perpanjang") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expandedTanggal = true }) {
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                    }
                }
            )

            DropdownMenu(
                expanded = expandedTanggal,
                onDismissRequest = { expandedTanggal = false }
            ) {
                tanggalOptions.forEach { tanggal ->
                    DropdownMenuItem(
                        text = { Text("Tanggal $tanggal") },
                        onClick = {
                            selectedTanggal = tanggal
                            expandedTanggal = false
                        }
                    )
                }
            }
        }

        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Pilih Gambar Bukti")
        }

        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
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
                Text("No image selected")
            }
        }

        Divider()

        Text("Data yang Diisi:", style = MaterialTheme.typography.titleMedium)
        Text("Motor: $selectedMotor")
        Text("Tanggal: $selectedTanggal")
        Text("Gambar: ${imageUri?.lastPathSegment ?: "Belum dipilih"}")

        Button(onClick = {
            if (imageUri != null) {
                val data = DataPerpanjangMotor(
                    name = selectedMotor,
                    room = selectedMotor,
                    month = selectedTanggal,
                    imageUri = imageUri?.toString()
                )

                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("submittedData", data)

                Toast.makeText(context, "Data telah tercatat!", Toast.LENGTH_SHORT).show()
                navController.navigate("DataPerpanjangMotor")
            } else {
                Toast.makeText(
                    context,
                    "Tolong unggah gambar bukti!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Submit")
        }
    }
}
