package com.pfflowers.ftharvest.ui.ingresoCosecha

import android.app.Application
import androidx.lifecycle.*
import com.pfflowers.ftharvest.db.MainDatabase
import com.pfflowers.ftharvest.pojos.Caja
import com.pfflowers.ftharvest.pojos.EncabezadoLectura
import com.pfflowers.ftharvest.pojos.LecturaCaja
import com.pfflowers.ftharvest.repositories.LecturaRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IngresoViewModel(application: Application) : AndroidViewModel(application) {
    fun insert(list: List<String>, usuario: Int, planta: Int) {

        with(_encabezado.value) {
            if (this != null) {
                if (boleta != "" && invernadero != "" && bodega != 0 && calidad != "" && semana != "" && lote != "" ) {

                    insertLectura(list = list, user = usuario, planta = planta)
                }
            }
        }
    }

    /**
     *
     */
    private fun insertLectura(
        list: List<String>,
        planta: Int,
        user: Int
    ): Job {
        return viewModelScope.launch {
            repository.insert(
                lecturaCaja = LecturaCaja(
                    codigo = list[1],
                    encabezado = _encabezado.value!!,
                    caja = Caja(
                        comun = list[2],
                        color = list[3],
                        variedad = list[4],
                        botonaje = list[5],
                        size = list[6],
                        tallos = list[7].toInt()
                    ),
                    empleado = user,
                    planta = planta
                )
            )
        }
    }

    private val db by lazy { MainDatabase.invoke(application) }
    private val repository by lazy { LecturaRepository(db.lecturaDao()) }


    private val _encabezado = MutableLiveData<EncabezadoLectura>().apply {
        value = EncabezadoLectura()
    }
    val lecturas = Transformations.switchMap(_encabezado) {
        repository.fetchLecturas(it)
        repository.lecturas
    }
    val resumenDiaGrupo = Transformations.switchMap(DoubleTrigger(_encabezado, lecturas)) {
        if (it.second != null) {
            repository.getResumenDiaGrupo(it.first!!)

        } else {
            null
        }
    }
    val resumenDia = Transformations.switchMap(DoubleTrigger(_encabezado, lecturas)) {
        if (it.second != null) {
            repository.getResumenDia(it.first!!)

        } else {
            null
        }
    }

    val lecturasEncabezado = Transformations.switchMap(DoubleTrigger(_encabezado, lecturas)) {
        it.first?.let { it1 -> repository.getLeturasEncabezado(it1) }
    }
    val encabezado: LiveData<EncabezadoLectura>
        get() {
            _encabezado.postValue(_encabezado.value)
            return _encabezado
        }


}
