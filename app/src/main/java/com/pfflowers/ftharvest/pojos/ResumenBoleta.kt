package com.pfflowers.ftharvest.pojos

import androidx.room.Embedded

data class ResumenBoleta(
    val boleta: String = "",
    val calidad: String,
    @Embedded val resumen: ResumenDia = ResumenDia()
)