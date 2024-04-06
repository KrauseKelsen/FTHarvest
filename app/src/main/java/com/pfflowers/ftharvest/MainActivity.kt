package com.pfflowers.ftharvest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pfflowers.ftharvest.adapters.MenuItemAdapter
import com.pfflowers.ftharvest.pojos.MenuItem

class MainActivity : AppCompatActivity(), MenuItemAdapter.Interaction {
    override fun onItemSelected(position: Int, item: MenuItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       
    }


}
