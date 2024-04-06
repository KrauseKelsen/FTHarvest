package com.pfflowers.ftharvest.pojos


import com.google.gson.annotations.SerializedName

data class Planta(
    @SerializedName("Activo")
    var activo: Boolean = false,
    @SerializedName("Descripcion")
    var descripcion: String = "",
    @SerializedName("PlantaId")
    var plantaId: Int = 0
)