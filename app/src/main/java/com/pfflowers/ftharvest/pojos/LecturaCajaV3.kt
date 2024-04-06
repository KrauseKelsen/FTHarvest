package com.pfflowers.ftharvest.pojos
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LecturaCajaV3(val codigo: String, val boleta: String, val invernadero: String, val cuadrilla: String,
                         val planta: Int, var bodega: Int, var empleado: Int, val calidad: String,
                         val op: String, var nave: Int, var semana: String, var lote: String) : Parcelable
