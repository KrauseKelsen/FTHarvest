package com.pfflowers.ftharvest.pojos


import com.google.gson.annotations.SerializedName

data class EncabezadoLectura(
    @SerializedName("Bodega")
    var bodega: Int = 0,
    @SerializedName("Boleta")
    var boleta: String = "",
    @SerializedName("Calidad")
    var calidad: String = "",
    @SerializedName("Cuadrilla")
    var cuadrilla: String = "",
    @SerializedName("Invernadero")
    var invernadero: String = "",
    @SerializedName("Nave")
    var nave: Int = 0,
    @SerializedName("Semana")
    var semana: String = "",
    @SerializedName("Lote")
    var lote: String = "",
    @SerializedName("Op")
    var op: String = ""
)