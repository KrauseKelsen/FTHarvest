package com.pfflowers.ftharvest.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.pojos.MenuItem
import kotlinx.android.synthetic.main.menu_item_layout.view.*
import kotlin.random.Random


class MenuItemAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MenuItem>() {

        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.destination == newItem.destination
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MenuItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.menu_item_layout,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MenuItemHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MenuItem>) {
        differ.submitList(list)
    }

    class MenuItemHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private var imageVew: ImageView
        private var optionName: TextView

        init {
            with(itemView) {
                imageVew = findViewById(R.id.img_option_icon)
                optionName = findViewById(R.id.txt_name)
            }
        }

        fun bind(item: MenuItem) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            imageVew.setImageResource(item.res)
            optionName.text = item.Name
            val rnd = Random
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            txt_item.setBackgroundColor(color)
            txt_item.text=(adapterPosition+1).toString()


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: MenuItem)
    }
}
