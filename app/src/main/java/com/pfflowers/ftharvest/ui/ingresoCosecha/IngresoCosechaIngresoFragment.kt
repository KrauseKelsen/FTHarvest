package com.pfflowers.ftharvest.ui.ingresoCosecha


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.LecturaCajaAdapter
import com.pfflowers.ftharvest.pojos.LecturaCaja
import java.util.*


/**
 * A simple [Fragment] subclass.
 * prConsultaCosechaHandheld
 * prInsertarCosechaHandheld
 */
class IngresoCosechaIngresoFragment : Fragment(), LecturaCajaAdapter.Interaction {
    override fun onItemSelected(position: Int, item: LecturaCaja) {

    }

    val USER_KEY: String = "preference_user"
    val PLANTA_KEY: String = "preference_planta"
    lateinit var preferences: SharedPreferences
    private lateinit var txtBoleta: TextView
    private lateinit var txtInvernadero: TextView
    private lateinit var txtNave: TextView
    private lateinit var txtSemana: TextView
    private lateinit var txtLote: TextView
    private lateinit var txtCalidad: TextView
    lateinit var txtBodega: TextView
    lateinit var rcyCajas: RecyclerView
    private lateinit var txtTallos: TextView
    lateinit var editLectura: EditText
    private lateinit var editbcup: EditText
    private lateinit var txtEtiquetas: TextView
    private lateinit var txtCuadrilla: TextView
    private lateinit var txtOp: TextView
    private lateinit var imgScan: ImageView
    lateinit var ingresoVm: IngresoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ingresoVm = ViewModelProvider(activity!!).get(IngresoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ingreso_cosecha_ingreso, container, false)
        initializeComponents(root)
        setObservers()
        setRecyclers()
        return root
    }

    override fun onResume() {
        super.onResume()
        editLectura.inputType = InputType.TYPE_NULL;
        editLectura.requestFocus();
    }
    private fun setRecyclers() {
        val adapterCajas = LecturaCajaAdapter()
        rcyCajas.adapter = adapterCajas
        rcyCajas.layoutManager = LinearLayoutManager(context)
        ingresoVm.lecturasEncabezado.observe(viewLifecycleOwner, Observer { t ->
            adapterCajas.submitList(t)
        })
    }

    private fun setObservers() {
        ingresoVm.encabezado.observe(viewLifecycleOwner, Observer {
            txtBoleta.text = it.boleta
            txtInvernadero.text = it.invernadero
            txtNave.text = it.nave.toString()
            txtSemana.text = it.semana.toString()
            txtLote.text = it.lote.toString()
            txtCalidad.text = it.calidad
            txtCuadrilla.text = it.cuadrilla
        })
        ingresoVm.resumenDiaGrupo.observe(viewLifecycleOwner, Observer {
            txtTallos.text = it?.tallos.toString()
            txtEtiquetas.text = it?.etiquetas.toString()
        })
    }

    override fun onStart() {
        super.onStart()
        if (editLectura.isFocused) editbcup.requestFocus()
        else editLectura.requestFocus()

    }

    private fun initializeComponents(view: View?) {

        view?.let {
            with(it) {
                txtBoleta = findViewById(R.id.txt_boleta)
                txtInvernadero = findViewById(R.id.txt_invernadero)
                txtNave = findViewById(R.id.txt_nave)
                txtSemana = findViewById(R.id.txt_semana)
                txtLote = findViewById(R.id.txt_lote)
                txtBodega = findViewById(R.id.txt_bodega)
                txtCuadrilla = findViewById(R.id.txt_cuadrilla)
                txtCalidad = findViewById(R.id.txt_calidad)
                txtOp = findViewById(R.id.txt_op)
                txtTallos = findViewById(R.id.txt_tallos)
                txtEtiquetas = findViewById(R.id.txt_codigo)
                editLectura = findViewById(R.id.edit_lectura)
                editbcup = findViewById(R.id.txtbcup)
                rcyCajas = findViewById(R.id.rcy_cajas)
                imgScan = findViewById(R.id.imageView3)
                preferences = PreferenceManager.getDefaultSharedPreferences(activity)
            }
        }
        editLectura.addTextChangedListener(textWatcherReading())
        editbcup.addTextChangedListener(textWatcherReading())
        imgScan.setOnClickListener {
            editLectura.requestFocus()
            Toast.makeText(context, "${editLectura.isFocused}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *
     */
    private fun textWatcherReading(): TextWatcher {
        return object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                try{
                    ingresoVm.encabezado.value!!.op=""
                    when {
                        s.isNotBlank() -> {
                            val spl = s.split(".")
                            when {
                                spl.size > 1 -> {
                                    //B.34
                                    //C.34
                                    //I.34
                                    //N.34
                                    //Q.34
                                    //W.34

                                    val selector = spl[0].toUpperCase(Locale.getDefault())
                                    val firstData = spl[1].toUpperCase(Locale.getDefault())
                                    when (selector) {
                                        "B" -> ingresoVm.encabezado.value?.boleta =
                                            firstData
                                        "C" -> ingresoVm.encabezado.value?.calidad =
                                            firstData
                                        "I" -> ingresoVm.encabezado.value?.invernadero =
                                            firstData
                                        "N" -> ingresoVm.encabezado.value?.nave =
                                            firstData.toInt()
                                        "S" -> ingresoVm.encabezado.value?.semana =
                                            firstData
                                        "L" -> ingresoVm.encabezado.value?.lote =
                                            firstData
                                        "Q" -> ingresoVm.encabezado.value?.cuadrilla =
                                            firstData
                                        "W" -> {
                                            ingresoVm.encabezado.value?.bodega = firstData.toInt()
                                            txtBodega.text = spl[2]
                                        }
                                        "X" -> {
                                            val user = preferences.getString(USER_KEY, "150")
                                            val planta = preferences.getString(PLANTA_KEY, "1")
                                            if (planta != null && user != null) {
                                                ingresoVm.insert(spl, user.toInt(), planta.toInt())
                                            }

                                        }
                                        else -> {
                                            var txt = ""
                                            for (x in spl) {
                                                txt += "$x\n"
                                            }
                                            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    editLectura.setText("", TextView.BufferType.EDITABLE)
                                    editbcup.setText("", TextView.BufferType.EDITABLE)
                                }
                                spl.isNotEmpty() -> {
                                    if (!spl[0].contains("$")) {
                                        editLectura.setText("", TextView.BufferType.EDITABLE)
                                        editbcup.setText("", TextView.BufferType.EDITABLE)
                                    } else {
                                        ingresoVm.encabezado.value?.op = spl[0]
                                    }
                                    Snackbar.make(rcyCajas, spl[0], Snackbar.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                    val view = activity?.currentFocus
                    view?.let { v ->
                        val imm =
                            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                        imm?.hideSoftInputFromWindow(v.windowToken, 0)
                    }
                    editLectura.requestFocus()
                }catch(e: Exception){
                    e.printStackTrace()
                }

            }


            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        }
    }


}
