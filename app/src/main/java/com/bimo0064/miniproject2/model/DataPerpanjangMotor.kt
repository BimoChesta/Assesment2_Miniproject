package com.bimo0064.miniproject2.model

import java.io.Serializable

data class DataPerpanjangMotor(
    val name: String,
    val room: String,
    val month: String,
    val imageUri: String?
) : Serializable
