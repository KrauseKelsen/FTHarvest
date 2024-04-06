package com.pfflowers.ftharvest.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.pfflowers.ftharvest.api.clients.ApiClient
import com.pfflowers.ftharvest.api.clients.ApiClientV2
import com.pfflowers.ftharvest.api.clients.InstanceRetrofit
import com.pfflowers.ftharvest.api.clients.sClLecturaCaja
import com.pfflowers.ftharvest.api.services.ILecturaCajaServiceV3
import com.pfflowers.ftharvest.db.dao.LecturaDao
import com.pfflowers.ftharvest.pojos.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.*

class LecturaRepository(private val lecturaDao: LecturaDao) {
    var job: CompletableJob? = null
    suspend fun insert(lecturaCaja: LecturaCaja) {
        lecturaDao.insert(lecturaCaja)
        fetchLecturas(lecturaCaja.encabezado)
    }

    fun fetchLecturas(encabezado: EncabezadoLectura) {

        job = Job()
        job?.let { completableJob ->
            CoroutineScope(IO + completableJob).launch {
                val ins: ApiResponse<LecturaCaja>
                if (isOnline()) {
                    try {
                        lecturas.value?.let {
                            //ins = ApiClient.sClLecturaCaja.InsertarLecturas(it)

                            val jsonNaves = ArrayList<LecturaCajaV3>()
                            it.forEach { lec -> jsonNaves.add(LecturaCajaV3(lec.codigo, lec.encabezado.boleta, lec.encabezado.invernadero,
                                lec.encabezado.cuadrilla, lec.planta, lec.encabezado.bodega, lec.empleado,
                                lec.encabezado.calidad, lec.encabezado.op, lec.encabezado.nave, lec.encabezado.semana, lec.encabezado.lote)) }

                            try {
                                val resu =
                                    InstanceRetrofit.instanceRetrofit!!.create(ILecturaCajaServiceV3::class.java)
                                        .post_lectura_caja(
                                            jsonNaves
                                        ).execute()
                                resu.isSuccessful
//                                ApiClientV2.sClLecturaCaja.post_lectura_caja(jsonNaves)
                            }catch (e : Exception){
                                e.printStackTrace()
                            }

                        }
                    } catch (ex: HttpException) {
                        val e = ex
                    }

                    val response = ApiClient.sClLecturaCaja.getLecturas(encabezado)
                    if (response.data.isNotEmpty()) {
                        insertFromApiList(*response.data.toTypedArray())
                    } else
                        Log.e("Consulta", "Nope ${response.Message}")
                }
                completableJob.complete()

            }

        }
    }

    fun fetchLecturas() {
        job = Job()
        job?.let { completableJob ->
            Log.e("fetching", "...")
            CoroutineScope(IO + completableJob).launch {
                if (isOnline()) {
                    try {
                        lecturasSinSubir.value?.let {
                            Log.i("insercion", " elementos ${it.size}")
                            it.forEach {
                                Log.i(
                                    "insercion: ",
                                    "\nCodigo: ${it.codigo}" +
                                            "\nEncabezado.Bodega: ${it.encabezado.bodega}" +
                                            "\nEncabezado.Boleta: ${it.encabezado.boleta}" +
                                            "\nEncabezado.Calidad: ${it.encabezado.calidad}" +
                                            "\nEncabezado.Cuadrilla: ${it.encabezado.cuadrilla}" +
                                            "\nEncabezado.Invernadero: ${it.encabezado.invernadero}" +
                                            "\nEncabezado.OP: ${it.encabezado.op}" +
                                            "\nEncabezado.Nave: ${it.encabezado.nave}" +
                                            "\nEncabezado.Semana: ${it.encabezado.semana}" +
                                            "\nEncabezado.Lote: ${it.encabezado.lote}" +
                                            "\nCaja.Botonaje: ${it.caja.botonaje}" +
                                            "\nCaja.Color: ${it.caja.color}" +
                                            "\nCaja.Comun: ${it.caja.comun}" +
                                            "\nCaja.Size: ${it.caja.size}" +
                                            "\nCaja.Tallos: ${it.caja.tallos}" +
                                            "\nCaja.Variedad: ${it.caja.variedad}" +
                                            "\nEstado: ${it.estado}" +
                                            "\nFecha: ${it.fecha}" +
                                            "\nPlanta: ${it.planta}" +
                                            "\nEmpleado: ${it.empleado}\n"
                                )
                            }

                            //val ins = ApiClient.sClLecturaCaja.InsertarLecturas(it)
                            val jsonNaves = ArrayList<LecturaCajaV3>()
                            it.forEach { lec ->
                                jsonNaves.add(
                                    LecturaCajaV3(
                                        lec.codigo,
                                        lec.encabezado.boleta,
                                        lec.encabezado.invernadero,
                                        lec.encabezado.cuadrilla,
                                        lec.planta,
                                        lec.encabezado.bodega,
                                        lec.empleado,
                                        lec.encabezado.calidad,
                                        lec.encabezado.op,
                                        lec.encabezado.nave,
                                        lec.encabezado.semana,
                                        lec.encabezado.lote
                                    )
                                )
                            }

                            try {
                                val resu =
                                    InstanceRetrofit.instanceRetrofit!!.create(ILecturaCajaServiceV3::class.java)
                                        .post_lectura_caja(
                                            jsonNaves
                                        ).execute()
                                resu.isSuccessful
//                                ApiClientV2.sClLecturaCaja.post_lectura_caja(jsonNaves)
                            }catch (e : Exception){
                                e.printStackTrace()
                            }

                        }
                    } catch (ex: HttpException) {
                        val e = ex
                    }
                    lecturasSinSubir.value?.let {
                        for (x in it) {
                            if (isOnline()) {
                                val response = ApiClient.sClLecturaCaja.getLecturas(x.encabezado)
                                if (response.data.isNotEmpty()) {
                                    insertFromApiList(*response.data.toTypedArray())
                                } else {
                                    Log.e("Consulta", "Nope ${response.Message}")
                                    insertFromApiList(*response.data.toTypedArray())
                                }
                            }else{
                                Log.e("Status","Offline")                           }
                        }
                        Log.e("insercion:", "End")

                    }

                }
                completableJob.complete()

            }

        }
    }

    private fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 192.168.200.105")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        return false

    }

    private suspend fun insertFromApiList(vararg lecturaCaja: LecturaCaja) {
        lecturaDao.insertfromApi(*lecturaCaja)
    }

    val lecturas = lecturaDao.getLecturas()
    val lecturasSinSubir = lecturaDao.getLecturasSinsubir()
    val boletasAtrasadas = lecturaDao.boletasAtrasadas()
    val comunes = lecturaDao.getComunes()
    val colores = lecturaDao.getColores()
    fun getResumenDiaGrupo(encabezado: EncabezadoLectura): LiveData<ResumenLecturas> {
        return lecturaDao.resumenDiaGrupo(
            encabezado.boleta, encabezado.invernadero, encabezado.calidad, encabezado
                .bodega
        )
    }

    fun getResumenDia(encabezado: EncabezadoLectura): LiveData<List<ResumenDia>> {
        return lecturaDao.resumenDia(
            encabezado.boleta, encabezado.invernadero, encabezado.calidad, encabezado
                .bodega
        )
    }

    fun getResumenCosechaDia(comun: String, color: String): LiveData<List<ResumenDia>> {
        return lecturaDao.resumenCosechaDia(
            comun, color, Date()
        )
    }

    fun getResumenCosechaDiaTotales(comun: String, color: String): LiveData<TotalesCosecha> {
        return lecturaDao.resumenCosechaDiaTotales(
            comun, color, Date()
        )
    }

    fun getLeturasEncabezado(encabezadoLectura: EncabezadoLectura): LiveData<List<LecturaCaja>> {
        return lecturaDao.leturasEncabezado(
            encabezadoLectura.boleta,
            encabezadoLectura.invernadero,
            encabezadoLectura.calidad,
            encabezadoLectura.bodega
        )
    }

    fun getResumenBoletas(): LiveData<List<ResumenBoleta>> {
        return lecturaDao.resumenBoletas(Date())
    }

    fun deleteLecturas() {
        lecturaDao.delLecturas()
    }

}