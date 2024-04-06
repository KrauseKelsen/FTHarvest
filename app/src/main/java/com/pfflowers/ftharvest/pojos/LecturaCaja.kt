package com.pfflowers.ftharvest.pojos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class LecturaCaja(
    @PrimaryKey
    @SerializedName("Codigo")
    var codigo: String = "",
    @Embedded
    @SerializedName("Encabezado")
    val encabezado: EncabezadoLectura,
    @Embedded
    @SerializedName("Caja")
    var caja: Caja = Caja(),
    @SerializedName("Estado")
    var estado: Boolean = false,
    @SerializedName("Fecha")
    val fecha: Date = Date(),
    @SerializedName("Planta")
    var planta: Int = 0,
    @SerializedName("Empleado")
    var empleado: Int = 0
)




