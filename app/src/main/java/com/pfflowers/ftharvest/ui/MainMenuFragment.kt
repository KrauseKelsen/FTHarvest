package com.pfflowers.ftharvest.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.adapters.MenuItemAdapter
import com.pfflowers.ftharvest.pojos.MenuItem
import kotlinx.android.synthetic.main.fragment_main_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : Fragment(), MenuItemAdapter.Interaction {
    override fun onItemSelected(position: Int, item: MenuItem) {
        val des = R.id.from_main_menu_to_nav
        if (item.destination != 0) {
            findNavController().navigate(item.destination)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_main_menu, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerMenu)
        val settings=root.findViewById<ImageView>(R.id.btn_settings)
        val adapter = MenuItemAdapter(this)
        val menuList = ArrayList<MenuItem>()
        recycler.layoutManager = LinearLayoutManager(context)
        settings.setOnClickListener {
            findNavController().navigate(R.id.from_main_menu_to_settings)
        }
        recycler.adapter = adapter
        initList(menuList)
        adapter.submitList(menuList)
        return root
    }

    private fun initList(list: ArrayList<MenuItem>) {
        val icons = resources.obtainTypedArray(R.array.item_icons)
        val names = resources.getStringArray(R.array.item_names)
        val directions = resources.obtainTypedArray(R.array.item_directions)

        for (i in 0 until directions.length()) {
            list.add(
                MenuItem(
                    when {
                        icons.length() > i -> icons.getResourceId(i, R.drawable.ic_tractor)
                        else -> R.drawable.ic_tractor
                    }
                    , when {
                        names.size > i -> names[i]
                        else -> "No set $i"
                    },
                    directions.getResourceId(i, 0)
                )
            )
        }
    }
}
