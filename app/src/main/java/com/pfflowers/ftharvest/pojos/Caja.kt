package com.pfflowers.ftharvest.pojos

import com.google.gson.annotations.SerializedName

data class Caja(

    @SerializedName("Comun")
    var comun: String = "",
    @SerializedName("Color")
    var color: String = "",
    @SerializedName("Variedad")
    var variedad: String = "",
    @SerializedName("Botonaje")
    var botonaje: String = "",
    @SerializedName("Size")
    var size: String = "",
    @SerializedName("Tallos")
    var tallos: Int = 0
) {
    fun nomenclatura(): String {
        return "$comun|$color|$variedad"
    }
}