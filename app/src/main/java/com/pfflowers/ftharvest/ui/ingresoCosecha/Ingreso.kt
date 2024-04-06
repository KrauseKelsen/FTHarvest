package com.pfflowers.ftharvest.ui.ingresoCosecha

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pfflowers.ftharvest.R

class Ingreso : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        val botomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        botomnav.setOnNavigationItemSelectedListener (this::setOnNavigationItemSelectedListener)
    }

    private fun setOnNavigationItemSelectedListener(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_resumen -> {
                findNavController(R.id.fragment2).navigate(R.id.ingresoCosechaResumenFragment)
                true
            }
            R.id.back_arrow -> {
                finish()
                true
            }
            R.id.btn_ingreso -> {
                findNavController(R.id.fragment2).navigate(R.id.ingresoCosechaIngresoFragment)
                true
            }
            else -> false
        }
    }


}
