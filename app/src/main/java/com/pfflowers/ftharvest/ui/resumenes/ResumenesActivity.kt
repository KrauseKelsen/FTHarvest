package com.pfflowers.ftharvest.ui.resumenes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pfflowers.ftharvest.R

class ResumenesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumenes)
        val bottomNav = findViewById<BottomNavigationView>(R.id.botton_nav_resumenes)
        bottomNav.setOnNavigationItemSelectedListener(this::setOnNavigationItemSelectedListener)
    }

    private fun setOnNavigationItemSelectedListener(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_boletas -> {
                findNavController(R.id.fragment_host_resumenes).popBackStack()
                findNavController(R.id.fragment_host_resumenes).navigate(R.id.resumenBoletasFragment)

                true
            }
            R.id.item_dia -> {
                findNavController(R.id.fragment_host_resumenes).popBackStack()
                findNavController(R.id.fragment_host_resumenes).navigate(R.id.resumenCosechaFragment)
                true
            }
            R.id.item_volver -> {
                finish()
                true
            }
            R.id.item_resagados -> {
                findNavController(R.id.fragment_host_resumenes).popBackStack()
                findNavController(R.id.fragment_host_resumenes).navigate(R.id.resagadosFragment)
                true
            }
            else -> false
        }
    }
}
