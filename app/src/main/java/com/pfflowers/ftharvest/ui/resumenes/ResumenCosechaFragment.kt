package com.pfflowers.ftharvest.ui.resumenes


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.ResumenDiaAdapter
import com.pfflowers.ftharvest.view_models.ResumenesViewModel
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ResumenCosechaFragment : Fragment() {
    private lateinit var comunes: Spinner
    private lateinit var colores: Spinner
    private lateinit var tallos: TextView
    private lateinit var etiquetas: TextView
    private lateinit var rcyCosecha: RecyclerView
    private lateinit var resumenesViewModel: ResumenesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_resumen_cosecha, container, false)
        initializeComponents(root)
        recyclers()
        observers()

        return root
    }

    private fun recyclers() {
        val adapter = ResumenDiaAdapter()
        rcyCosecha.adapter = adapter
        rcyCosecha.layoutManager = LinearLayoutManager(context)

    }

    private fun observers() {
        context?.let { cont: Context ->
            observe(resumenesViewModel.comunes) {
                val array = ArrayList<String>()
                array.add("")
                array.addAll(it)
                val adapter =
                    ArrayAdapter<String>(
                        cont, R.layout.spinner_item,
                        array
                    )
                adapter.setDropDownViewResource(R.layout.spinner_item)
                comunes.adapter = adapter
            }
            observe(resumenesViewModel.colores) {
                val array = ArrayList<String>()
                array.add("")
                array.addAll(it)
                val adapter =
                    ArrayAdapter<String>(
                        cont, R.layout.spinner_item,
                        array
                    )
                adapter.setDropDownViewResource(R.layout.spinner_item)
                colores.adapter = adapter
            }
            observe(resumenesViewModel.cosechaDia) {
                val adapter: ResumenDiaAdapter = rcyCosecha.adapter as ResumenDiaAdapter
                adapter.submitList(it)
            }
            observe(resumenesViewModel.totalesCosecha) {
                tallos.text = it.tallos.toString()
                etiquetas.text = it.etiquetas.toString()
            }
        }
    }

    private fun <T> observe(
        observable: LiveData<T>,
        observ: (t: T) -> Unit
    ) {
        observable.observe(viewLifecycleOwner, Observer(observ))
    }

    private fun initializeComponents(root: View) = with(root) {

        resumenesViewModel = ViewModelProvider(activity!!).get(ResumenesViewModel::class.java)
        comunes = findViewById(R.id.spin_comunes)
        comunes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    resumenesViewModel.comun.postValue(p0.getItemAtPosition(p2) as String)
                }
            }

        }
        colores = findViewById(R.id.spin_colores)
        colores.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    resumenesViewModel.color.postValue(p0.getItemAtPosition(p2) as String)
                }
            }

        }
        rcyCosecha = findViewById(R.id.rcy_cosechahoy)
        tallos = findViewById(R.id.txt_tallos)
        etiquetas = findViewById(R.id.txt_codigo)


    }




}
