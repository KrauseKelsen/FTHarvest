package com.pfflowers.ftharvest.ui.resumenes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.ResumenBoletasAdapter
import com.pfflowers.ftharvest.view_models.ResumenesViewModel

/**
 * A simple [Fragment] subclass.
 */
class ResumenBoletasFragment : Fragment() {

    private lateinit var resumenesViewModel: ResumenesViewModel
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_resumen_boletas, container, false)
        initializeComponents(root)
        recyclers()
        observers()
        return root
    }

    private fun observers() {
        observe(resumenesViewModel.resumenBoletasHoy) {
            val adapter = recycler.adapter as ResumenBoletasAdapter
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
        val adapter = ResumenBoletasAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun initializeComponents(root: View) {
        with(root) {
            resumenesViewModel = ViewModelProvider(activity!!).get(ResumenesViewModel::class.java)
            recycler = findViewById(R.id.rcy_resumen_boletas_dia)
        }
    }


}
