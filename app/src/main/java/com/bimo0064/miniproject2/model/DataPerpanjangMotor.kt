package com.bimo0064.miniproject2.model

import java.io.Serializable

data class DataPerpanjangMotor(
    val nama: String,
    val kamar: String,
    val bulan: String,
    val imageUri: String?
) : Serializable