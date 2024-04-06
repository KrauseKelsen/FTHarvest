package com.pfflowers.ftharvest.ui.resumenes


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.LecturaCajaAdapter
import com.pfflowers.ftharvest.view_models.ResumenesViewModel
import kotlinx.android.synthetic.main.fragment_resagados.*

/**
 * A simple [Fragment] subclass.
 */
class ResagadosFragment : Fragment() {

    private lateinit var resumenesViewModel: ResumenesViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var btnCargar: Button
    private lateinit var btnDelete: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_resagados, container, false)
        initializeComponents(root)
        recyclers()
        observers()
        return root
    }

    private fun observers() {
        observe(resumenesViewModel.lecturasSinSubir) {
            val adapter = recycler.adapter as LecturaCajaAdapter
            adapter.submitList(it)
        }
    }

    private fun <T> observe(
        observable: LiveData<T>,
        observ: (t: T) -> Unit
    ) {
        observable.observe(viewLifecycleOwner, Observer(observ))
    }

    private fun recyclers() {
        val adapter = LecturaCajaAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun initializeComponents(root: View) = with(root) {
        resumenesViewModel = ViewModelProvider(activity!!).get(ResumenesViewModel::class.java)

        btnCargar = findViewById(R.id.btn_cargar)
        btnDelete = findViewById(R.id.btn_delete)
        recycler = findViewById(R.id.rcy_resagados)
        btnCargar.setOnClickListener {
            resumenesViewModel.fetchLecturas()
        }

        btnDelete.setOnClickListener {
            if(editTextTextPassword.text.toString()=="Pyf.1209"){
                AsyncTask.execute {
                    resumenesViewModel.delLecturas()
                }
            }else{
                Toast.makeText(context, "Contraseña invalida...", Toast.LENGTH_LONG).show()
            }
            editTextTextPassword.clear()
        }
    }

    fun EditText.clear() {
        text.clear()
    }
}
