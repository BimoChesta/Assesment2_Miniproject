package com.bimo0064.miniproject2.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun InformasiScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F0F2))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobiasa),
            contentDescription = "Logo Kost",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                InfoCard(
                    title = "Alamat Kost",
                    content = """
                        Wisma Kania, Gang Amdasari, RT.5/RW.1,
                        Kampung Managa Dua, Dayeuhkolot (Belakang Mixue Sukapura)
                        DAYEUHKOLOT, KAB.BANDUNG, JAWA BARAT, ID 40257
                    """.trimIndent()
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                InfoCard(
                    title = "Fasilitas & Kewajiban",
                    content = """
                        Fasilitas Kamar:
                        - Kasur
                        - Headboard kasur
                        - Bantal & Guling
                        - Meja
                        - Lemari
                        - Kamar mandi dalam
                        - Ember & Gayung

                        Fasilitas Umum:
                        - Dapur
                        - Alat masak
                        - Kulkas
                        - Rooftop
                        - Jemuran pakaian
                        - Ruang santai

                        Kewajiban:
                        - Membayar listrik setiap bulan
                    """.trimIndent()
                )
            }
        }
    }
}

@Composable
fun InfoCard(title: String, content: String) {
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