package com.pfflowers.ftharvest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pfflowers.ftharvest.pojos.*
import java.util.*

@Dao
interface LecturaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lecturaCaja: LecturaCaja)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertfromApi(vararg lecturaCaja: LecturaCaja)

    @Query("Select * from LecturaCaja")
    fun getLecturas(): LiveData<List<LecturaCaja>>

    @Query("Delete From LecturaCaja")
    fun delLecturas()

    @Query("Select * from LecturaCaja where estado=0")
    fun getLecturasSinsubir(): LiveData<List<LecturaCaja>>

    @Query("Select distinct color from LecturaCaja")
    fun getColores(): LiveData<List<String>>

    @Query("Select distinct comun from LecturaCaja")
    fun getComunes(): LiveData<List<String>>

    @Query("select * from LecturaCaja where boleta=:boleta and invernadero=:invernadero and calidad =:calidad and bodega=:bodega ")
    fun leturasEncabezado(
        boleta: String,
        invernadero: String,
        calidad: String,
        bodega: Int
    ): LiveData<List<LecturaCaja>>

    @Query("select sum(tallos) tallos, count(codigo) etiquetas from LecturaCaja where boleta=:boleta and invernadero=:invernadero and calidad=:calidad and bodega=:bodega")
    fun resumenDiaGrupo(
        boleta: String,
        invernadero: String,
        calidad: String,
        bodega: Int
    ): LiveData<ResumenLecturas>

    @Query("select sum(tallos) tallos, count(codigo) etiquetas, comun,color,variedad,botonaje from LecturaCaja where boleta=:boleta and invernadero=:invernadero and calidad=:calidad and bodega=:bodega group by comun,color,variedad")
    fun resumenDia(
        boleta: String,
        invernadero: String,
        calidad: String,
        bodega: Int
    ): LiveData<List<ResumenDia>>

    @Query("select sum(tallos) tallos, count(codigo) etiquetas, comun,color,variedad, botonaje from LecturaCaja where comun like :comun||'%' and color like :color||'%' and fecha=:fecha group by comun,color")
    fun resumenCosechaDia(
        comun: String,
        color: String,
        fecha: Date
    ): LiveData<List<ResumenDia>>

    @Query("select sum(tallos) tallos, count(codigo) etiquetas from LecturaCaja where comun like :Comun||'%' and color like :color||'%'  and fecha=:fecha")
    fun resumenCosechaDiaTotales(
        Comun: String,
        color: String,
        fecha: Date
    ): LiveData<TotalesCosecha>

    @Query("Select boleta ,  sum(tallos) tallos,count(codigo) etiquetas,comun,color,variedad,calidad,botonaje from LecturaCaja  where fecha=:fecha group by boleta,comun,variedad,color,calidad,botonaje order by boleta")
    fun resumenBoletas(fecha: Date): LiveData<List<ResumenBoleta>>

    @Query("Select boleta from LecturaCaja where estado=0 group by boleta")
    fun boletasAtrasadas(): LiveData<List<String>>
}