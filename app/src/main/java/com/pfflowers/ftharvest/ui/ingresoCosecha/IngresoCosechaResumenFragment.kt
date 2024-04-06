package com.pfflowers.ftharvest.ui.ingresoCosecha


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.ResumenDiaAdapter

/**
 * A simple [Fragment] subclass.
 */
class IngresoCosechaResumenFragment : Fragment() {

    lateinit var txtBoleta: TextView
    lateinit var txtInvernadero: TextView
    lateinit var txtCalidad: TextView
    lateinit var txtBodega: TextView
    lateinit var ingresoVm: IngresoViewModel
    lateinit var recyclerCajas: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingresoVm = ViewModelProvider(activity!!).get(IngresoViewModel::class.java)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_ingreso_cosecha_resumen, container, false)
        initializeComponents(root)
        setObservers()
        setRecyclers()

        return root
    }

    private fun setRecyclers() {
        val adapterResumen = ResumenDiaAdapter()
        recyclerCajas.adapter = adapterResumen
        recyclerCajas.layoutManager = LinearLayoutManager(context)
        ingresoVm.resumenDia.observe(viewLifecycleOwner, Observer {
            adapterResumen.submitList(it)
        })
    }

    private fun setObservers() {
        ingresoVm.encabezado.observe(viewLifecycleOwner, Observer {
            txtBoleta.text=it.boleta
            txtBodega.text=it.bodega.toString()
            txtCalidad.text=it.calidad
            txtInvernadero.text=it.invernadero
        })

    }

    private fun initializeComponents(root: View) = with(root) {
        txtBodega = findViewById(R.id.txt_bodega)
        txtInvernadero = findViewById(R.id.txt_invernadero)
        txtCalidad = findViewById(R.id.txt_calidad)
        txtBoleta = findViewById(R.id.txt_boleta)
        recyclerCajas = findViewById(R.id.rcy_cajas)
    }


}
