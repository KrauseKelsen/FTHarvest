package com.pfflowers.ftharvest.ui.ingresoCosecha


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.pfflowers.ftharvest.R

/**
 * A simple [Fragment] subclass.
 */
class IngresoCosechaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_ingreso_cosecha, container, false)
        initializeComponents(root)
        return root
    }

    private fun initializeComponents(view: View?) {

    }

}
