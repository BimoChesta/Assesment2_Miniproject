package com.bimo0064.miniproject2.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bimo0064.miniproject2.R

@Composable
fun AturanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F0F2))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobiasa),
            contentDescription = "Logo Sewa Motor",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Baris 2 Kolom
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                InfoCardSewaMotor(
                    title = "Syarat Sewa Motor",
                    content = """
                        1. Menyediakan KTP asli dan fotokopi.
                        2. Melakukan pembayaran sesuai tarif sewa.
                        3. Mengembalikan motor tepat waktu.
                        4. Menjaga kebersihan dan kondisi motor.
                    """.trimIndent()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun InfoCardSewaMotor(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFF2B9E9E),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        )
    }
}