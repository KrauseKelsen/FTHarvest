package com.pfflowers.ftharvest.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.pfflowers.ftharvest.db.MainDatabase
import com.pfflowers.ftharvest.pojos.LecturaCaja
import com.pfflowers.ftharvest.repositories.LecturaRepository
import com.pfflowers.ftharvest.ui.ingresoCosecha.DoubleTrigger
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ResumenesViewModel(application: Application) : AndroidViewModel(application) {
    private val db by lazy { MainDatabase.invoke(application) }
    private val repository by lazy { LecturaRepository(db.lecturaDao()) }
    private val _comun: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = ""
        }
    }
    val comun: MutableLiveData<String>
        get() = _comun
    private val _color: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = ""
        }
    }
    val color: MutableLiveData<String>
        get() = _color
    val comunes by lazy { repository.comunes }
    val colores = repository.colores
    val lecturasSinSubir = repository.lecturasSinSubir
    val cosechaDia = Transformations.switchMap(DoubleTrigger(comun, color)) {
        it.second?.let { it1 -> it.first?.let { it2 -> repository.getResumenCosechaDia(it2, it1) } }
    }
    val totalesCosecha = Transformations.switchMap(DoubleTrigger(comun, color)) {
        it.second?.let { it1 ->
            it.first?.let { it2 ->
                repository.getResumenCosechaDiaTotales(
                    it2,
                    it1
                )
            }
        }
    }
    val resumenBoletasHoy by lazy { repository.getResumenBoletas() }
    fun fetchLecturas() {
        repository.fetchLecturas()
    }

    fun delLecturas() {
        repository.deleteLecturas()
    }
}